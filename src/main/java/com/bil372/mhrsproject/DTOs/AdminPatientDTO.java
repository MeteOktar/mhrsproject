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
    private String email; // Assuming email exists or will be empty
    private String phone; // Assuming phone exists or will be empty
}
