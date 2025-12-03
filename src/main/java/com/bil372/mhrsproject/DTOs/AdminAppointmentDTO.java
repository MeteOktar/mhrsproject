package com.bil372.mhrsproject.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminAppointmentDTO {
    private String id;
    private String patientName;
    private String doctorName;
    private LocalDateTime date;
    private String status;
}
