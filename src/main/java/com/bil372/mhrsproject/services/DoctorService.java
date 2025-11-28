package com.bil372.mhrsproject.services;

import org.springframework.stereotype.Service;

import com.bil372.mhrsproject.DTOs.DoctorDTO;
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

    public DoctorDTO toDoctorDTO(Doctor doctor) {
        DoctorDTO dto = new DoctorDTO();
        dto.setDoctorNationalId(doctor.getDoctorNationalId());
        dto.setFirstName(doctor.getFirstName());
        dto.setLastName(doctor.getLastName());
        dto.setHospital(doctor.getHospital());
        dto.setDepartment(doctor.getDepartment());
        return dto;
    }
}
