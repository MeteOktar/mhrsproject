package com.bil372.mhrsproject.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bil372.mhrsproject.entities.AppointmentSlot;
import com.bil372.mhrsproject.entities.Doctor;
import com.bil372.mhrsproject.entities.Patient;
import com.bil372.mhrsproject.entities.Prescription;


public interface PrescriptionsRepository extends JpaRepository<Prescription, Integer>{

    Optional<Prescription> findByPrescriptionId(int prescriptionId);

    List<Prescription> findByAppointmentSlot(AppointmentSlot appointment);
    
    List<Prescription> findByAppointmentSlot_AppointmentId(int appointmentId);

    List<Prescription> findByPatient(Patient patient);

    List<Prescription> findByPatient_PatientNationalId(long patientNationalId);

    List<Prescription> findByDoctor(Doctor doctor);

    List<Prescription> findByDoctor_DoctorNationalId(long doctorNationalId);
} 
