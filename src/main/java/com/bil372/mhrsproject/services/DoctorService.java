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
    
    public DoctorService(DoctorRepository doctorRepository){
        this.doctorRepository = doctorRepository;
    }

    public Doctor getDoctorByNational(long nationalId){
        Doctor doctor = doctorRepository.findByDoctorNationalId(nationalId).orElseThrow(() -> new RuntimeException("Doctor not found"));
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
}
