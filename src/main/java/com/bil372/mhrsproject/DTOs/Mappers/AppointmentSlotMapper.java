package com.bil372.mhrsproject.DTOs.Mappers;

import java.util.List;
import java.util.stream.Collectors;

import com.bil372.mhrsproject.DTOs.ASlotDTO;
import com.bil372.mhrsproject.entities.AppointmentSlot;
import com.bil372.mhrsproject.entities.Doctor;
import com.bil372.mhrsproject.entities.Hospital;
import com.bil372.mhrsproject.entities.HospitalDepartment;
import com.bil372.mhrsproject.entities.Patient;
import com.bil372.mhrsproject.entities.Prescription;

public class AppointmentSlotMapper {

    public static ASlotDTO toASlotDTO(AppointmentSlot slot) {
        ASlotDTO dto = new ASlotDTO();

        // Slot
        dto.setId(slot.getAppointmentId());
        dto.setSlotDateTime(slot.getSlotDateTime());
        dto.setStatus(slot.getStatus());

        // Doctor
        Doctor doctor = slot.getDoctor();
        if (doctor != null) {
            dto.setDoctorNationalId(doctor.getDoctorNationalId());
            dto.setDoctorFirstName(doctor.getFirstName()); 
            dto.setDoctorLastName(doctor.getLastName());
        }

        // Hospital
        Hospital hospital = slot.getHospital();
        if (hospital != null) {
            dto.setHospitalId(hospital.getHospitalId());
            dto.setHospitalName(hospital.getName());
            dto.setHospitalCity(hospital.getCity());
        }

        // Department
        HospitalDepartment department = slot.getDepartment();
        if (department != null) {
            dto.setDepartmentId(department.getDepartmentId());
            dto.setDepartmentName(department.getBranchName());
        }

        // Patient
        Patient patient = slot.getPatient();
        if (patient != null) {
            dto.setPatientNationalId(patient.getPatientNationalId());
            dto.setPatientFirstName(patient.getFirstName());
            dto.setPatientLastName(patient.getLastName());
        }

         List<Prescription> prescriptions = slot.getAppointmentPrescriptions();
        if (prescriptions != null && !prescriptions.isEmpty()) {
            Prescription p = prescriptions.get(0);
            String drugsText = p.getPrescribedDrugs()
            .stream()
            .map(d -> d.getDrugName() + " - " + d.getDosage())
            .collect(Collectors.joining(", "));
            dto.setPrescriptionText(p.getDiagnosis() + ". \n Yazılan İlaçlar: \n" + drugsText + "\n"+ p.getNotes());
        } else {
            dto.setPrescriptionText("");
        }
        return dto;
    }

    public static List<ASlotDTO> toASlotDTOList(List<AppointmentSlot> slots) {
        return slots.stream()
                .map(AppointmentSlotMapper::toASlotDTO)
                .collect(Collectors.toList());
    }
}
