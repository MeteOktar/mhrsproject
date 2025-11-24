package com.bil372.mhrsproject.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bil372.mhrsproject.entities.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long> {

    Optional<Patient> findByPatientNationalId(long patientNationalId);
}
