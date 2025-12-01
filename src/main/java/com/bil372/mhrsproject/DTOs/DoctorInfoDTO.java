package com.bil372.mhrsproject.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoctorInfoDTO {
    private String firstName;
    private String lastName;
    private String nationalId;
    private String hospitalName;
    private String departmentName;
}
