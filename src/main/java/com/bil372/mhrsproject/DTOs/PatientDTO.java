package com.bil372.mhrsproject.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientDTO {

    private long nationalId;
    private String firstName;
    private String lastName;
    private int heightCm;
    private int weightKg;
    private String bloodType;

}
