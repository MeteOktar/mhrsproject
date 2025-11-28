package com.bil372.mhrsproject.controllers;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bil372.mhrsproject.DTOs.PatientDTO;
import com.bil372.mhrsproject.DTOs.WaitingListDTO;
import com.bil372.mhrsproject.entities.AppointmentSlot;
import com.bil372.mhrsproject.entities.Patient;
import com.bil372.mhrsproject.entities.Prescription;
import com.bil372.mhrsproject.services.AppointmentSlotsService;
import com.bil372.mhrsproject.services.PatientService;
import com.bil372.mhrsproject.services.PrescriptionsService;
import com.bil372.mhrsproject.services.WaitingListService;
import com.bil372.mhrsproject.services.security.MyUserDetails;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/patient")
public class PatientController {

    private final AppointmentSlotsService appointmentSlotsService;
    private final WaitingListService waitingListService;
    private final PrescriptionsService prescriptionsService;
    private final PatientService patientService;

    public PatientController(WaitingListService waitingListService, PrescriptionsService prescriptionsService, PatientService patientService, AppointmentSlotsService appointmentSlotsService) {
        this.waitingListService = waitingListService;
        this.prescriptionsService = prescriptionsService;
        this.patientService = patientService;
        this.appointmentSlotsService = appointmentSlotsService;
    }

    @GetMapping("/waiting-list")
    public List<WaitingListDTO> getMyWaitingList(Authentication authentication) {
        String username = authentication.getName();           // nationalId
        long patientNationalId = Long.parseLong(username);     // longa dondu
        return waitingListService.getPatientWaitingList(patientNationalId);
    }

    @GetMapping("/prescriptions")
    public List<Prescription> getPrescriptions(@AuthenticationPrincipal MyUserDetails user) {
        long patientNationalId = user.getNationalId();
        return prescriptionsService.getPatientPrescriptionsByNationalId(patientNationalId);
    }

    @GetMapping("/info")
    public PatientDTO getPatientDTO(@AuthenticationPrincipal MyUserDetails user) {
        Patient patient = patientService.getPatientByNationalId(user.getNationalId());
        PatientDTO pDto = patientService.toPatientDTO(patient);
        return pDto;
    }

    @GetMapping("/appointments")
    public List<AppointmentSlot> getPatientAppointmentSlots(@AuthenticationPrincipal MyUserDetails user) {
        long patientNationalId = user.getNationalId();
        return appointmentSlotsService.getPatientAppointmentSlots(patientNationalId);
    }
    

}

