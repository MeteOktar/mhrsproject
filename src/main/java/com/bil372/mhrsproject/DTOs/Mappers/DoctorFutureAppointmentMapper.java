package com.bil372.mhrsproject.DTOs.Mappers;

import java.util.List;
import java.util.stream.Collectors;

import com.bil372.mhrsproject.DTOs.DoctorFutureAppointmentDTO;
import com.bil372.mhrsproject.entities.AppointmentSlot;
import com.bil372.mhrsproject.entities.Patient;

public class DoctorFutureAppointmentMapper {

    public static DoctorFutureAppointmentDTO toFutureDTO(AppointmentSlot slot) {
        DoctorFutureAppointmentDTO dto = new DoctorFutureAppointmentDTO();

        // Basic
        dto.setId(slot.getAppointmentId());
        dto.setDateTime(slot.getSlotDateTime());

        // Patient
        Patient patient = slot.getPatient();
        if (patient != null) {
            dto.setPatientFirstName(patient.getFirstName());
            dto.setPatientLastName(patient.getLastName());
        }

        // Cancelled status
        String status = slot.getStatus();
        dto.setStatus(status);

        return dto;
    }

    public static List<DoctorFutureAppointmentDTO> toFutureDTOList(List<AppointmentSlot> slots) {
        return slots.stream()
                .map(DoctorFutureAppointmentMapper::toFutureDTO)
                .collect(Collectors.toList());
    }
}
