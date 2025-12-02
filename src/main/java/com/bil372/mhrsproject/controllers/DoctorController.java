package com.bil372.mhrsproject.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.bil372.mhrsproject.DTOs.DoctorFutureAppointmentDTO;
import com.bil372.mhrsproject.DTOs.DoctorInfoDTO;
import com.bil372.mhrsproject.DTOs.DoctorListDTO;
import com.bil372.mhrsproject.DTOs.DoctorPastAppointmentDTO;
import com.bil372.mhrsproject.DTOs.DoctorSlotDTO;
import com.bil372.mhrsproject.DTOs.PrescriptionsDTO;
import com.bil372.mhrsproject.DTOs.WaitingListDTO;
import com.bil372.mhrsproject.DTOs.Mappers.DoctorFutureAppointmentMapper;
import com.bil372.mhrsproject.DTOs.Mappers.DoctorPastAppointmentMapper;
import com.bil372.mhrsproject.DTOs.Mappers.PrescriptionMapper;
import com.bil372.mhrsproject.entities.AppointmentSlot;
import com.bil372.mhrsproject.entities.Doctor;
import com.bil372.mhrsproject.entities.Prescription;
import com.bil372.mhrsproject.services.AppointmentSlotsService;
import com.bil372.mhrsproject.services.DoctorService;
import com.bil372.mhrsproject.services.PrescriptionsService;
import com.bil372.mhrsproject.services.WaitingListService;
import com.bil372.mhrsproject.services.security.MyUserDetails;



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
    public List<WaitingListDTO> getDoctorWaitingList(@AuthenticationPrincipal MyUserDetails user) {
        long doctorNationalId = user.getNationalId();
        return waitingListService.getDoctorWaitingList(doctorNationalId);
    }

    @PostMapping("/waiting-lists/{waitingId}/cancel")
    public ResponseEntity<Void> cancelWaitingList(@PathVariable int waitingId) {
        waitingListService.cancelWaitingListByDoctor(waitingId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/prescriptions")
    public List<PrescriptionsDTO> getPrescriptions(@AuthenticationPrincipal MyUserDetails user) {
        long doctorNationalId = user.getNationalId();
        List<Prescription> prescriptions = prescriptionsService.getDoctorPrescriptionsByNationalId(doctorNationalId);
        return PrescriptionMapper.toDTOList(prescriptions);
    }

    @GetMapping("/past-appointments")
    public List<DoctorPastAppointmentDTO> getPastAppointments(@AuthenticationPrincipal MyUserDetails user) {
        long docId = user.getNationalId();
        List<AppointmentSlot> past = appointmentSlotsService.getDoctorPastAppointmentSlots(docId);
        return DoctorPastAppointmentMapper.toPastDTOList(past);
    }

    @GetMapping("/future-appointments")
    public List<DoctorFutureAppointmentDTO> getFutureAppointments(@AuthenticationPrincipal MyUserDetails user) {
        long doctorId = user.getNationalId();
        List<AppointmentSlot> future = appointmentSlotsService.getDoctorFutureAppointmentSlots(doctorId);
        return DoctorFutureAppointmentMapper.toFutureDTOList(future);
    }

    @GetMapping("/info")
    public DoctorInfoDTO getDoctorInfoDTO(@AuthenticationPrincipal MyUserDetails user) {
        Doctor doctor = doctorService.getDoctorByNational(user.getNationalId());
        DoctorInfoDTO doctorDTO = doctorService.toDoctorInfoDTO(doctor);
        return doctorDTO;
    }
    
    @PostMapping("/appointments/{appointmentId}/cancel")
    public ResponseEntity<Void> cancelAppointment(@PathVariable int appointmentId) {
        appointmentSlotsService.cancelAppointmentByDoctor(appointmentId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/by-department")
    public List<DoctorListDTO> getDoctorsByDepartment(@RequestParam int departmentId) {
        List<Doctor> doctors = doctorService.getDoctorsByDepartment(departmentId);

        return doctors.stream()
                .map(d -> new DoctorListDTO(
                        d.getDoctorNationalId(),
                        d.getFirstName(),
                        d.getLastName(),
                        d.getDepartment().getDepartmentId(),
                        d.getHospital().getHospitalId()
                ))
                .toList();
    }

    @GetMapping("/slots")
    public List<DoctorSlotDTO> getDoctorSlots(
            @RequestParam long doctorNationalId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date
    ) {
        List<AppointmentSlot> slots =
                appointmentSlotsService.getSlotsByDoctorAndDate(doctorNationalId, date);

        return slots.stream()
                .map(s -> new DoctorSlotDTO(
                        s.getAppointmentId(),
                        s.getSlotDateTime(),
                        s.getStatus()
                ))
                .toList();
    }
}

