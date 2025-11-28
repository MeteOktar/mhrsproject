package com.bil372.mhrsproject.DTOs;

import com.bil372.mhrsproject.entities.Hospital;
import com.bil372.mhrsproject.entities.HospitalDepartment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoctorDTO {

    private long doctorNationalId;
    private String firstName;
    private String lastName;
    private Hospital hospital;
    private HospitalDepartment department;
}
