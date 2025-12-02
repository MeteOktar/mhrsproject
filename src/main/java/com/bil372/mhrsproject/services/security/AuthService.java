package com.bil372.mhrsproject.services.security;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bil372.mhrsproject.DTOs.LoginRequest;
import com.bil372.mhrsproject.DTOs.LoginResponse;
import com.bil372.mhrsproject.repositories.DoctorRepository;
import com.bil372.mhrsproject.repositories.PatientRepository;
import com.bil372.mhrsproject.repositories.AdminRepository;
import com.bil372.mhrsproject.entities.Admin;

@Service
public class AuthService {

    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(PatientRepository patientRepository,
                       DoctorRepository doctorRepository,
                       AdminRepository adminRepository,
                       PasswordEncoder passwordEncoder,
                       JwtService jwtService) {
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
        this.adminRepository = adminRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public LoginResponse login(LoginRequest request) {
        String role = request.getUserType();
        if (role == null) {
            throw new RuntimeException("User type is required");
        }

        // ---- PATIENT ----
        if ("PATIENT".equalsIgnoreCase(role)) {
            if (request.getNationalId() == null) {
                throw new RuntimeException("NationalId is required for PATIENT");
            }

            var patient = patientRepository
                    .findByPatientNationalId(request.getNationalId())
                    .orElseThrow(() -> new RuntimeException("Patient not found"));

            if (!passwordEncoder.matches(request.getPassword(), patient.getPasswordHash())) {
                throw new RuntimeException("Invalid credentials");
            }

            String token = jwtService.generateToken(
                    String.valueOf(patient.getPatientNationalId()),
                    "PATIENT"
            );

            LoginResponse res = new LoginResponse();
            res.setToken(token);
            res.setRole("PATIENT");
            res.setFirstName(patient.getFirstName());
            res.setLastName(patient.getLastName());
            return res;
        }

        // ---- DOCTOR ----
        if ("DOCTOR".equalsIgnoreCase(role)) {
            if (request.getNationalId() == null) {
                throw new RuntimeException("NationalId is required for DOCTOR");
            }

            var doctor = doctorRepository
                    .findByDoctorNationalId(request.getNationalId())
                    .orElseThrow(() -> new RuntimeException("Doctor not found"));

            if (!passwordEncoder.matches(request.getPassword(), doctor.getPasswordHash())) {
                throw new RuntimeException("Invalid credentials");
            }

            String token = jwtService.generateToken(
                    String.valueOf(doctor.getDoctorNationalId()),
                    "DOCTOR"
            );

            LoginResponse res = new LoginResponse();
            res.setToken(token);
            res.setRole("DOCTOR");
            res.setFirstName(doctor.getFirstName());
            res.setLastName(doctor.getLastName());
            return res;
        }

        // ---- ADMIN ----
        if ("ADMIN".equalsIgnoreCase(role)) {
            if (request.getUsername() == null || request.getUsername().isBlank()) {
                throw new RuntimeException("Username is required for ADMIN");
            }

            Admin admin = adminRepository
                    .findByUsername(request.getUsername())
                    .orElseThrow(() -> new RuntimeException("Admin not found"));

            if (!passwordEncoder.matches(request.getPassword(), admin.getPasswordHash())) {
                throw new RuntimeException("Invalid credentials");
            }

            String token = jwtService.generateToken(
                    admin.getUsername(),   // subject = username (String)
                    "ADMIN"
            );

            LoginResponse res = new LoginResponse();
            res.setToken(token);
            res.setRole("ADMIN");
            res.setFirstName("Admin");
            res.setLastName(admin.getUsername());

            return res;
        }

        throw new RuntimeException("Unknown user type: " + role);
    }
}
