package com.bil372.mhrsproject.services;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.bil372.mhrsproject.DTOs.PatientDTO;
import com.bil372.mhrsproject.DTOs.PatientRegisterRequest;
import com.bil372.mhrsproject.DTOs.Mappers.PatientMapper;
import com.bil372.mhrsproject.entities.Patient;
import com.bil372.mhrsproject.repositories.PatientRepository;

@Service
public class PatientService {

    private final PatientRepository patientRepository;
    private final PasswordEncoder passwordEncoder;

    public PatientService(PatientRepository patientRepository, PasswordEncoder passwordEncoder) {
        this.patientRepository = patientRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Patient getPatientByNationalId(long nationalId) {
        Patient patient = patientRepository.findByPatientNationalId(nationalId)
                .orElseThrow(() -> new RuntimeException("patient not found"));
        return patient;
    }

    public PatientDTO toPatientDTO(Patient patient) {
        PatientDTO patientDTO = new PatientDTO();
        patientDTO.setBloodGroup(patient.getBloodType());
        patientDTO.setFirstName(patient.getFirstName());
        patientDTO.setHeightCm(patient.getHeightCm());
        patientDTO.setLastName(patient.getLastName());
        patientDTO.setNationalId(patient.getPatientNationalId());
        patientDTO.setWeightKg(patient.getWeightKg());
        return patientDTO;
    }

    public PatientDTO register(PatientRegisterRequest req) {

        if (patientRepository.existsByPatientNationalId(req.getNationalId())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "Bu T.C. ile zaten kayıtlı bir hasta var.");
        }

        Patient p = new Patient();
        p.setPatientNationalId(req.getNationalId());
        p.setFirstName(req.getFirstName());
        p.setLastName(req.getLastName());
        p.setHeightCm(req.getHeightCm());
        p.setWeightKg(req.getWeightKg());
        p.setBloodType(req.getBloodGroup());

        p.setPasswordHash(passwordEncoder.encode(req.getPassword()));

        Patient saved = patientRepository.save(p);
        return PatientMapper.toPatientDTO(saved);
    }

    // Admin Methods
    public java.util.List<com.bil372.mhrsproject.DTOs.AdminPatientDTO> getAllPatients() {
        return patientRepository.findAll().stream()
                .map(p -> new com.bil372.mhrsproject.DTOs.AdminPatientDTO(
                        String.valueOf(p.getPatientNationalId()),
                        p.getFirstName(),
                        p.getLastName(),
                        "", // Email not in entity
                        "" // Phone not in entity
                ))
                .toList();
    }
}
