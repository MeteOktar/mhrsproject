/*package com.bil372.mhrsproject.config;

import com.bil372.mhrsproject.entities.Patient;
import com.bil372.mhrsproject.entities.Doctor;
import com.bil372.mhrsproject.entities.Admin;
import com.bil372.mhrsproject.repositories.PatientRepository;
import com.bil372.mhrsproject.repositories.DoctorRepository;
import com.bil372.mhrsproject.repositories.AdminRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserPasswordInitializer implements CommandLineRunner {

    private final PatientRepository patientRepo;
    private final DoctorRepository doctorRepo;
    private final AdminRepository adminRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {

        String defaultPassword = "123456";
        String hashed = passwordEncoder.encode(defaultPassword);

        int patientCount = 0;
        int doctorCount = 0;
        int adminCount = 0;

        // PATIENTS
        var patients = patientRepo.findAll();
        for (Patient p : patients) {
            if (p.getPasswordHash() == null || p.getPasswordHash().isBlank()) {
                p.setPasswordHash(hashed);
                patientCount++;
            }
        }
        patientRepo.saveAll(patients);

        // DOCTORS
        var doctors = doctorRepo.findAll();
        for (Doctor d : doctors) {
            if (d.getPasswordHash() == null || d.getPasswordHash().isBlank()) {
                d.setPasswordHash(hashed);
                doctorCount++;
            }
        }
        doctorRepo.saveAll(doctors);

        // ADMINS
        var admins = adminRepo.findAll();
        for (Admin a : admins) {
            if (a.getPasswordHash() == null || a.getPasswordHash().isBlank()) {
                a.setPasswordHash(hashed);
                adminCount++;
            }
        }
        adminRepo.saveAll(admins);

        System.out.println(">>> Initialized passwordHash fields:");
        System.out.println("    Patients updated: " + patientCount);
        System.out.println("    Doctors updated: " + doctorCount);
        System.out.println("    Admins updated: " + adminCount);
    }
}*/
