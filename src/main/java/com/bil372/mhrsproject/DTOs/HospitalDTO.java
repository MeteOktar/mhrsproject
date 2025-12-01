package com.bil372.mhrsproject.DTOs;

import java.util.List;

import com.bil372.mhrsproject.entities.Doctor;
import com.bil372.mhrsproject.entities.HospitalDepartment;

import lombok.Data;

@Data
public class HospitalDTO {

    private List<HospitalDepartment> hospitalDepartments;

    private List<Doctor> hospitalDoctors;

}
