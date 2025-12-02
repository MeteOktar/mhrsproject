package com.bil372.mhrsproject.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoctorListDTO {
    private long doctorNationalId;
    private String firstName;
    private String lastName;
    private int departmentId;
    private int hospitalId;
}
