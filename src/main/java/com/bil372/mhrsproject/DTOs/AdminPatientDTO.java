package com.bil372.mhrsproject.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminPatientDTO {
    private String nationalId;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String bloodGroup;
    private int heightCm;
    private int weightKg;
}
