package com.bil372.mhrsproject.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bil372.mhrsproject.DTOs.ASlotDTO;
import com.bil372.mhrsproject.DTOs.PatientDTO;
import com.bil372.mhrsproject.DTOs.PatientRegisterRequest;
import com.bil372.mhrsproject.DTOs.PrescriptionsDTO;
import com.bil372.mhrsproject.DTOs.WaitingListDTO;
import com.bil372.mhrsproject.DTOs.Mappers.AppointmentSlotMapper;
import com.bil372.mhrsproject.DTOs.Mappers.PrescriptionMapper;
import com.bil372.mhrsproject.entities.AppointmentSlot;
import com.bil372.mhrsproject.entities.Patient;
import com.bil372.mhrsproject.entities.Prescription;
import com.bil372.mhrsproject.services.AppointmentSlotsService;
import com.bil372.mhrsproject.services.PatientService;
import com.bil372.mhrsproject.services.PrescriptionsService;
import com.bil372.mhrsproject.services.WaitingListService;
import com.bil372.mhrsproject.services.security.MyUserDetails;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;


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
    public List<PrescriptionsDTO> getPrescriptions(@AuthenticationPrincipal MyUserDetails user) {
        long patientNationalId = user.getNationalId();
        List<Prescription> prescriptions = prescriptionsService.getPatientPrescriptionsByNationalId(patientNationalId);
        return PrescriptionMapper.toDTOList(prescriptions);
    }

    @GetMapping("/info")
    public PatientDTO getPatientDTO(@AuthenticationPrincipal MyUserDetails user) {
        Patient patient = patientService.getPatientByNationalId(user.getNationalId());
        PatientDTO pDto = patientService.toPatientDTO(patient);
        return pDto;
    }

    @GetMapping("/past-appointments")
    public List<ASlotDTO> getPatientPastAppointmentSlots(@AuthenticationPrincipal MyUserDetails user) {
        long patientNationalId = user.getNationalId();
        List<AppointmentSlot> slots = appointmentSlotsService.getPatientPastAppointmentSlots(patientNationalId);
        return AppointmentSlotMapper.toASlotDTOList(slots);
    }
    @GetMapping("/future-appointments")
    public List<ASlotDTO> getPatientFutureAppointmentSlots(@AuthenticationPrincipal MyUserDetails user) {
        long patientNationalId = user.getNationalId();
        List<AppointmentSlot> slots = appointmentSlotsService.getPatientFutureAppointmentSlots(patientNationalId);
        return AppointmentSlotMapper.toASlotDTOList(slots);
    }
    
    @PostMapping("/appointments/{appointmentId}/cancel")
    public ResponseEntity<Void> cancelAppointment(@PathVariable int appointmentId) {
        appointmentSlotsService.cancelAppointmentByPatient(appointmentId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/waiting-lists/{waitingId}/cancel")
    public ResponseEntity<Void> cancelWaitingList(@PathVariable int waitingId) {
        waitingListService.cancelWaitingListByPatient(waitingId);
        return ResponseEntity.ok().build();
    }
    

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public PatientDTO register(@RequestBody PatientRegisterRequest req) {
        return patientService.register(req);
    }

    @PostMapping("/appointments/{appointmentId}/book")
    public ResponseEntity<Void> bookAppointment(
            @PathVariable int appointmentId,
            @AuthenticationPrincipal MyUserDetails user
    ) {
        long patientNationalId = user.getNationalId();
        appointmentSlotsService.bookAppointment(appointmentId, patientNationalId);
        return ResponseEntity.ok().build();
    }
}

