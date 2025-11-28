package com.bil372.mhrsproject.services;

import org.springframework.stereotype.Service;

import com.bil372.mhrsproject.DTOs.PatientDTO;
import com.bil372.mhrsproject.entities.Patient;
import com.bil372.mhrsproject.repositories.PatientRepository;

@Service
public class PatientService {

    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository){
        this.patientRepository = patientRepository;
    }

    public Patient getPatientByNationalId(long nationalId){
        Patient patient = patientRepository.findByPatientNationalId(nationalId).orElseThrow(() -> new RuntimeException("patient not found"));
        return patient;
    }

    public PatientDTO toPatientDTO(Patient patient){
        PatientDTO patientDTO = new PatientDTO();
        patientDTO.setBloodType(patient.getBloodType());
        patientDTO.setFirstName(patient.getFirstName());
        patientDTO.setHeightCm(patient.getHeightCm());
        patientDTO.setLastName(patient.getLastName());
        patientDTO.setNationalId(patient.getPatientNationalId());
        patientDTO.setWeightKg(patient.getWeightKg());
        return patientDTO;
    }
}
