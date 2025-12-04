package com.bil372.mhrsproject.services;

import org.springframework.stereotype.Service;

import com.bil372.mhrsproject.DTOs.AdminDashboardSummaryDTO;
import com.bil372.mhrsproject.repositories.HospitalRepository;
import com.bil372.mhrsproject.repositories.HospitalDepartmentRepository;
import com.bil372.mhrsproject.repositories.DoctorRepository;
import com.bil372.mhrsproject.repositories.PatientRepository;
import com.bil372.mhrsproject.repositories.AppointmentSlotsRepository;
import com.bil372.mhrsproject.repositories.WaitingListRepository;

@Service
public class AdminDashboardService {

    private final HospitalRepository hospitalRepository;
    private final HospitalDepartmentRepository hospitalDepartmentRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;
    private final AppointmentSlotsRepository appointmentSlotsRepository;
    private final WaitingListRepository waitingListRepository;

    private final com.bil372.mhrsproject.repositories.AdminRepository adminRepository;
    private final org.springframework.security.crypto.password.PasswordEncoder passwordEncoder;

    public AdminDashboardService(
            HospitalRepository hospitalRepository,
            HospitalDepartmentRepository hospitalDepartmentRepository,
            DoctorRepository doctorRepository,
            PatientRepository patientRepository,
            AppointmentSlotsRepository appointmentSlotsRepository,
            WaitingListRepository waitingListRepository,
            com.bil372.mhrsproject.repositories.AdminRepository adminRepository,
            org.springframework.security.crypto.password.PasswordEncoder passwordEncoder) {

        this.hospitalRepository = hospitalRepository;
        this.hospitalDepartmentRepository = hospitalDepartmentRepository;
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
        this.appointmentSlotsRepository = appointmentSlotsRepository;
        this.waitingListRepository = waitingListRepository;
        this.adminRepository = adminRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public AdminDashboardSummaryDTO getSummary() {
        AdminDashboardSummaryDTO dto = new AdminDashboardSummaryDTO();

        dto.setTotalHospitals(hospitalRepository.count());
        dto.setTotalDepartments(hospitalDepartmentRepository.count());
        dto.setTotalDoctors(doctorRepository.count());
        dto.setTotalPatients(patientRepository.count());
        dto.setTotalAppointments(appointmentSlotsRepository.count());
        dto.setTotalActiveAppointments(appointmentSlotsRepository.countByStatus("booked"));
        dto.setTotalWaitingList(waitingListRepository.count());
        return dto;
    }

    public java.util.List<com.bil372.mhrsproject.DTOs.AdminUserDTO> getAllAdmins() {
        return adminRepository.findAll().stream()
                .map(a -> new com.bil372.mhrsproject.DTOs.AdminUserDTO(
                        a.getUsername(),
                        "ADMIN",
                        "" // Password not returned for security
                ))
                .toList();
    }

    public com.bil372.mhrsproject.DTOs.AdminUserDTO createAdmin(com.bil372.mhrsproject.DTOs.AdminUserDTO dto) {
        if (adminRepository.findByUsername(dto.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exists");
        }

        com.bil372.mhrsproject.entities.Admin admin = new com.bil372.mhrsproject.entities.Admin();
        admin.setUsername(dto.getUsername());
        // Use password from DTO if provided, otherwise default
        String rawPassword = (dto.getPassword() != null && !dto.getPassword().isBlank())
                ? dto.getPassword()
                : "123456";
        admin.setPasswordHash(passwordEncoder.encode(rawPassword));

        com.bil372.mhrsproject.entities.Admin saved = adminRepository.save(admin);

        return new com.bil372.mhrsproject.DTOs.AdminUserDTO(saved.getUsername(), "ADMIN", "");
    }

    public void deleteAdmin(String username) {
        com.bil372.mhrsproject.entities.Admin admin = adminRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Admin not found"));
        adminRepository.delete(admin);
    }
}
