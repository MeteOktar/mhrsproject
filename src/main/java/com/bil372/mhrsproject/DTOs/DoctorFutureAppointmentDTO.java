package com.bil372.mhrsproject.DTOs;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class DoctorFutureAppointmentDTO {

    private int id;                      // appointmentId
    private LocalDateTime dateTime;      // slotDateTime

    private String patientFirstName;
    private String patientLastName;

    private String status;         // status == CANCELLED
}
