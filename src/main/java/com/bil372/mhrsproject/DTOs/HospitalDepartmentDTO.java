package com.bil372.mhrsproject.DTOs;

public class HospitalDepartmentDTO {
    private int departmentId;
    private String branchName;

    public HospitalDepartmentDTO(int departmentId, String branchName) {
        this.departmentId = departmentId;
        this.branchName = branchName;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public String getBranchName() {
        return branchName;
    }
}
