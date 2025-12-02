package com.bil372.mhrsproject.DTOs;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoctorSlotDTO {
    private int appointmentId;
    private LocalDateTime slotDateTime;
    private String status; // EMPTY, BOOKED, CANCELLED gibi enum stringi
}
