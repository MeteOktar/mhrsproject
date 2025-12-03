package com.bil372.mhrsproject.DTOs;

import lombok.Data;

@Data
public class CreateHospitalDepartmentRequest {
    private int hospitalId;
    private String branchName;
}
