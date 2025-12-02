package com.bil372.mhrsproject.DTOs.Mappers;

import com.bil372.mhrsproject.DTOs.PatientDTO;
import com.bil372.mhrsproject.entities.Patient;

public class PatientMapper {

    public static PatientDTO toPatientDTO(Patient p) {
        return new PatientDTO(
            p.getPatientNationalId(),
            p.getFirstName(),
            p.getLastName(),
            p.getHeightCm(),
            p.getWeightKg(),
            p.getBloodType()
        );
    }
}
