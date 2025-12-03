package com.bil372.mhrsproject.DTOs;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminPrescriptionDTO {
    private int prescriptionId;
    private LocalDateTime prescriptionDateTime;
    private String doctorName;
    private String patientName;
    private String hospitalName;
    private String departmentName;
    private String diagnosis;
    private String notes;
    private java.util.List<String> drugs;
}
