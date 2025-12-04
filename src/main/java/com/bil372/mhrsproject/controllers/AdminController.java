package com.bil372.mhrsproject.controllers;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bil372.mhrsproject.DTOs.AdminAppointmentDTO;
import com.bil372.mhrsproject.DTOs.AdminDashboardSummaryDTO;
import com.bil372.mhrsproject.DTOs.AdminDepartmentDTO;
import com.bil372.mhrsproject.DTOs.AdminDoctorDTO;
import com.bil372.mhrsproject.DTOs.AdminHospitalDTO;
import com.bil372.mhrsproject.DTOs.AdminPatientDTO;
import com.bil372.mhrsproject.DTOs.AdminPrescriptionDTO;
import com.bil372.mhrsproject.DTOs.AdminUserDTO;
import com.bil372.mhrsproject.DTOs.WaitingListDTO;
import com.bil372.mhrsproject.services.AdminDashboardService;
import com.bil372.mhrsproject.services.AppointmentSlotsService;
import com.bil372.mhrsproject.services.DoctorService;
import com.bil372.mhrsproject.services.HospitalService;
import com.bil372.mhrsproject.services.PatientService;
import com.bil372.mhrsproject.services.PrescriptionsService;
import com.bil372.mhrsproject.services.WaitingListService;

@RestController
@RequestMapping("/api/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    private final WaitingListService waitingListService;
    private final AdminDashboardService adminDashboardService;
    private final DoctorService doctorService;
    private final HospitalService hospitalService;
    private final PatientService patientService;
    private final AppointmentSlotsService appointmentSlotsService;
    private final PrescriptionsService prescriptionsService;

    public AdminController(WaitingListService waitingListService,
            AdminDashboardService adminDashboardService,
            DoctorService doctorService,
            HospitalService hospitalService,
            PatientService patientService,
            AppointmentSlotsService appointmentSlotsService,
            PrescriptionsService prescriptionsService) {
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
    public List<AdminDoctorDTO> getAllDoctors() {
        return doctorService.getAllDoctors();
    }

    @PostMapping("/doctors")
    public AdminDoctorDTO createDoctor(@RequestBody AdminDoctorDTO dto) {
        return doctorService.createDoctor(dto);
    }

    @DeleteMapping("/doctors/{id}")
    public void deleteDoctor(@PathVariable String id) {
        doctorService.deleteDoctor(id);
    }

    // Hospital & Department Management
    @GetMapping("/hospitals")
    public List<AdminHospitalDTO> getAllHospitals() {
        return hospitalService.getAllHospitalsAsDTO();
    }

    @GetMapping("/departments")
    public List<AdminDepartmentDTO> getAllDepartments() {
        return hospitalService.getAllDepartmentsAsDTO();
    }

    @PostMapping("/hospitals")
    public AdminHospitalDTO createHospital(@RequestBody AdminHospitalDTO dto) {
        return hospitalService.createHospital(dto);
    }

    @DeleteMapping("/hospitals/{id}")
    public void deleteHospital(@PathVariable String id) {
        hospitalService.deleteHospital(id);
    }

    @PostMapping("/departments")
    public AdminDepartmentDTO createDepartment(@RequestBody AdminDepartmentDTO dto) {
        return hospitalService.createDepartment(dto);
    }

    @DeleteMapping("/departments/{id}")
    public void deleteDepartment(@PathVariable String id) {
        hospitalService.deleteDepartment(id);
    }

    // Patient Management
    @GetMapping("/patients")
    public List<AdminPatientDTO> getAllPatients() {
        return patientService.getAllPatients();
    }

    @PostMapping("/patients")
    public AdminPatientDTO createPatient(@RequestBody AdminPatientDTO dto) {
        return patientService.createPatient(dto);
    }

    @DeleteMapping("/patients/{id}")
    public void deletePatient(@PathVariable String id) {
        patientService.deletePatient(id);
    }

    // Appointment Logs
    @GetMapping("/appointments")
    public List<AdminAppointmentDTO> getAllAppointments(
            @RequestParam(required = false) String dateFrom,
            @RequestParam(required = false) String dateTo,
            @RequestParam(required = false) String search,
            @RequestParam(required = false) String status,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return appointmentSlotsService.getAllAppointments(dateFrom, dateTo, search, status, page, size);
    }

    // Prescriptions
    @GetMapping("/prescriptions")
    public List<AdminPrescriptionDTO> getAllPrescriptions(
            @RequestParam(defaultValue = "2000") int limit) {
        return prescriptionsService.getAllPrescriptions(limit);
    }

    @DeleteMapping("/prescriptions/{id}")
    public void deletePrescription(@PathVariable int id) {
        prescriptionsService.deletePrescription(id);
    }

    // Admin Users
    @GetMapping("/users")
    public List<AdminUserDTO> getAllAdmins() {
        return adminDashboardService.getAllAdmins();
    }

    @PostMapping("/users")
    public AdminUserDTO createAdmin(@RequestBody AdminUserDTO dto) {
        return adminDashboardService.createAdmin(dto);
    }

    @DeleteMapping("/users/{id}")
    public void deleteAdmin(@PathVariable String id) {
        adminDashboardService.deleteAdmin(id);
    }

    @DeleteMapping("/waiting-list/{id}")
    public void deleteWaitingList(@PathVariable int id) {
        waitingListService.deleteWaitingList(id);
    }
}
