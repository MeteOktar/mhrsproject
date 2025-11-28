package com.bil372.mhrsproject.controllers;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bil372.mhrsproject.DTOs.DoctorDTO;
import com.bil372.mhrsproject.DTOs.WaitingListDTO;
import com.bil372.mhrsproject.entities.AppointmentSlot;
import com.bil372.mhrsproject.entities.Doctor;
import com.bil372.mhrsproject.entities.Prescription;
import com.bil372.mhrsproject.services.AppointmentSlotsService;
import com.bil372.mhrsproject.services.DoctorService;
import com.bil372.mhrsproject.services.PrescriptionsService;
import com.bil372.mhrsproject.services.WaitingListService;
import com.bil372.mhrsproject.services.security.MyUserDetails;
import org.springframework.web.bind.annotation.RequestParam;




@RestController
@RequestMapping("/api/doctor")
public class DoctorController {

    private final WaitingListService waitingListService;
    private final DoctorService doctorService;
    private final PrescriptionsService prescriptionsService;
    private final AppointmentSlotsService appointmentSlotsService;

    public DoctorController(WaitingListService waitingListService, DoctorService doctorService, PrescriptionsService prescriptionsService, AppointmentSlotsService appointmentSlotsService) {
        this.waitingListService = waitingListService;
        this.doctorService = doctorService;
        this.prescriptionsService = prescriptionsService;
        this.appointmentSlotsService = appointmentSlotsService;
    }

    @GetMapping("/waiting-list")
    public List<WaitingListDTO> getMyWaitingList(@AuthenticationPrincipal MyUserDetails user) {
        long doctorNationalId = user.getNationalId();
        return waitingListService.getDoctorWaitingList(doctorNationalId);
    }

    @GetMapping("/prescriptions")
    public List<Prescription> getPrescriptions(@AuthenticationPrincipal MyUserDetails user) {
        long doctorNationalId = user.getNationalId();
        return prescriptionsService.getDoctorPrescriptionsByNationalId(doctorNationalId);
    }

    @GetMapping("/appointments")
    public List<AppointmentSlot> getAppointmentSlots(@AuthenticationPrincipal MyUserDetails user) {
        long doctorNationalId = user.getNationalId();
        return appointmentSlotsService.getDoctorAppointmentSlots(doctorNationalId);
    }
    
    
    @GetMapping("/info")
    public DoctorDTO getDoctorDTO(@AuthenticationPrincipal MyUserDetails user) {
        Doctor doctor = doctorService.getDoctorByNational(user.getNationalId());
        DoctorDTO doctorDTO = doctorService.toDoctorDTO(doctor);
        return doctorDTO;
    }
    
}

