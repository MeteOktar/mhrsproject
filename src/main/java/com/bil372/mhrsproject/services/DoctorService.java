package com.bil372.mhrsproject.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bil372.mhrsproject.DTOs.DoctorDTO;
import com.bil372.mhrsproject.DTOs.DoctorInfoDTO;
import com.bil372.mhrsproject.entities.Doctor;
import com.bil372.mhrsproject.repositories.DoctorRepository;

@Service
public class DoctorService {
    private final DoctorRepository doctorRepository;
    private final com.bil372.mhrsproject.repositories.HospitalRepository hospitalRepository;
    private final com.bil372.mhrsproject.repositories.HospitalDepartmentRepository departmentRepository;
    private final org.springframework.security.crypto.password.PasswordEncoder passwordEncoder;

    public DoctorService(DoctorRepository doctorRepository,
            com.bil372.mhrsproject.repositories.HospitalRepository hospitalRepository,
            com.bil372.mhrsproject.repositories.HospitalDepartmentRepository departmentRepository,
            org.springframework.security.crypto.password.PasswordEncoder passwordEncoder) {
        this.doctorRepository = doctorRepository;
        this.hospitalRepository = hospitalRepository;
        this.departmentRepository = departmentRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Doctor getDoctorByNational(long nationalId) {
        Doctor doctor = doctorRepository.findByDoctorNationalId(nationalId)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));
        return doctor;
    }

    public DoctorInfoDTO toDoctorInfoDTO(Doctor doctor) {
        DoctorInfoDTO dto = new DoctorInfoDTO();
        dto.setNationalId(String.valueOf(doctor.getDoctorNationalId()));
        dto.setFirstName(doctor.getFirstName());
        dto.setLastName(doctor.getLastName());
        dto.setHospitalName(doctor.getHospital().getName());
        dto.setDepartmentName(doctor.getDepartment().getBranchName());
        return dto;
    }

    public List<Doctor> getDoctorsByDepartment(int departmentId) {
        return doctorRepository.findByDepartment_DepartmentId(departmentId);
    }

    // Admin Methods
    public List<com.bil372.mhrsproject.DTOs.AdminDoctorDTO> getAllDoctors() {
        return doctorRepository.findAll().stream()
                .map(d -> new com.bil372.mhrsproject.DTOs.AdminDoctorDTO(
                        String.valueOf(d.getDoctorNationalId()), // Using nationalId as ID for now, or we can use a
                                                                 // separate ID if it existed
                        d.getFirstName(),
                        d.getLastName(),
                        String.valueOf(d.getDoctorNationalId()),
                        String.valueOf(d.getHospital().getHospitalId()),
                        String.valueOf(d.getDepartment().getDepartmentId())))
                .toList();
    }

    public void createDoctor(com.bil372.mhrsproject.DTOs.AdminDoctorDTO dto) {
        Doctor doctor = new Doctor();
        doctor.setDoctorNationalId(Long.parseLong(dto.getNationalId()));
        doctor.setFirstName(dto.getFirstName());
        doctor.setLastName(dto.getLastName());

        // Default password for new doctors
        doctor.setPasswordHash(passwordEncoder.encode("123456"));

        com.bil372.mhrsproject.entities.Hospital hospital = hospitalRepository
                .findById(Integer.parseInt(dto.getHospitalId()))
                .orElseThrow(() -> new RuntimeException("Hospital not found"));
        doctor.setHospital(hospital);

        com.bil372.mhrsproject.entities.HospitalDepartment department = departmentRepository
                .findById(Integer.parseInt(dto.getDepartmentId()))
                .orElseThrow(() -> new RuntimeException("Department not found"));
        doctor.setDepartment(department);

        doctorRepository.save(doctor);
    }

    public void deleteDoctor(String id) {
        // ID is nationalId in this context
        doctorRepository.deleteById(Long.parseLong(id));
    }
}
