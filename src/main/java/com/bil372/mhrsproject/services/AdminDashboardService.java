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

    public AdminDashboardService(
            HospitalRepository hospitalRepository,
            HospitalDepartmentRepository hospitalDepartmentRepository,
            DoctorRepository doctorRepository,
            PatientRepository patientRepository,
            AppointmentSlotsRepository appointmentSlotsRepository,
            WaitingListRepository waitingListRepository) {

        this.hospitalRepository = hospitalRepository;
        this.hospitalDepartmentRepository = hospitalDepartmentRepository;
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
        this.appointmentSlotsRepository = appointmentSlotsRepository;
        this.waitingListRepository = waitingListRepository;
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
}
