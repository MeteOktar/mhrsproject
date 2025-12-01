package com.bil372.mhrsproject.DTOs;

import lombok.Data;

@Data
public class AdminDashboardSummaryDTO {

    private long totalHospitals;
    private long totalDepartments;
    private long totalDoctors;
    private long totalPatients;
    private long totalAppointments;
    private long totalActiveAppointments;
    private long totalWaitingList;

}
