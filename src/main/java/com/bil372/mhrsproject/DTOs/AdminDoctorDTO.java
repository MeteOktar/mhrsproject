package com.bil372.mhrsproject.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminDoctorDTO {
    private String id; // Using String to match requirement "doc1", though DB uses long
    private String firstName;
    private String lastName;
    private String nationalId;
    private String hospitalId;
    private String departmentId;
}
