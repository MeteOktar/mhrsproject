package com.bil372.mhrsproject.controllers;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bil372.mhrsproject.DTOs.AdminDashboardSummaryDTO;
import com.bil372.mhrsproject.DTOs.WaitingListDTO;
import com.bil372.mhrsproject.services.AdminDashboardService;
import com.bil372.mhrsproject.services.WaitingListService;

@RestController
@RequestMapping("/api/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    private final WaitingListService waitingListService;
    private final AdminDashboardService adminDashboardService;
    private final com.bil372.mhrsproject.services.DoctorService doctorService;
    private final com.bil372.mhrsproject.services.HospitalService hospitalService;
    private final com.bil372.mhrsproject.services.PatientService patientService;
    private final com.bil372.mhrsproject.services.AppointmentSlotsService appointmentSlotsService;
    private final com.bil372.mhrsproject.services.PrescriptionsService prescriptionsService;

    public AdminController(WaitingListService waitingListService,
            AdminDashboardService adminDashboardService,
            com.bil372.mhrsproject.services.DoctorService doctorService,
            com.bil372.mhrsproject.services.HospitalService hospitalService,
            com.bil372.mhrsproject.services.PatientService patientService,
            com.bil372.mhrsproject.services.AppointmentSlotsService appointmentSlotsService,
            com.bil372.mhrsproject.services.PrescriptionsService prescriptionsService) {
        this.waitingListService = waitingListService;
        this.adminDashboardService = adminDashboardService;
        this.doctorService = doctorService;
        this.hospitalService = hospitalService;
        this.patientService = patientService;
        this.appointmentSlotsService = appointmentSlotsService;
        this.prescriptionsService = prescriptionsService;
    }

    @GetMapping("/waiting-list")
    public List<WaitingListDTO> getMyWaitingList(Authentication authentication) {
        return waitingListService.getAdminWaitingList();
    }

    @GetMapping("/dashboardSummary")
    public AdminDashboardSummaryDTO getAdminDashboardSummaryDTO(Authentication authentication) {
        return adminDashboardService.getSummary();
    }

    // Doctor Management
    @GetMapping("/doctors")
    public List<com.bil372.mhrsproject.DTOs.AdminDoctorDTO> getAllDoctors() {
        return doctorService.getAllDoctors();
    }

    @org.springframework.web.bind.annotation.PostMapping("/doctors")
    public void createDoctor(
            @org.springframework.web.bind.annotation.RequestBody com.bil372.mhrsproject.DTOs.AdminDoctorDTO dto) {
        doctorService.createDoctor(dto);
    }

    @org.springframework.web.bind.annotation.DeleteMapping("/doctors/{id}")
    public void deleteDoctor(@org.springframework.web.bind.annotation.PathVariable String id) {
        doctorService.deleteDoctor(id);
    }

    // Hospital & Department Management
    @GetMapping("/hospitals")
    public List<com.bil372.mhrsproject.DTOs.AdminHospitalDTO> getAllHospitals() {
        return hospitalService.getAllHospitalsAsDTO();
    }

    @GetMapping("/departments")
    public List<com.bil372.mhrsproject.DTOs.AdminDepartmentDTO> getAllDepartments() {
        return hospitalService.getAllDepartmentsAsDTO();
    }

    // Patient Management
    @GetMapping("/patients")
    public List<com.bil372.mhrsproject.DTOs.AdminPatientDTO> getAllPatients() {
        return patientService.getAllPatients();
    }

    // Appointment Logs
    @GetMapping("/appointments")
    public List<com.bil372.mhrsproject.DTOs.AdminAppointmentDTO> getAllAppointments(
            @org.springframework.web.bind.annotation.RequestParam(required = false) String dateFrom,
            @org.springframework.web.bind.annotation.RequestParam(required = false) String dateTo,
            @org.springframework.web.bind.annotation.RequestParam(required = false) String search,
            @org.springframework.web.bind.annotation.RequestParam(required = false) String status) {
        return appointmentSlotsService.getAllAppointments(dateFrom, dateTo, search, status);
    }

    // Prescriptions
    @GetMapping("/prescriptions")
    public List<com.bil372.mhrsproject.DTOs.AdminPrescriptionDTO> getAllPrescriptions(
            @org.springframework.web.bind.annotation.RequestParam(defaultValue = "2000") int limit) {
        return prescriptionsService.getAllPrescriptions(limit);
    }

    // Admin Users
    @GetMapping("/users")
    public List<com.bil372.mhrsproject.DTOs.AdminUserDTO> getAllAdmins() {
        return adminDashboardService.getAllAdmins();
    }
}
