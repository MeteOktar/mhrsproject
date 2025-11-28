package com.bil372.mhrsproject.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bil372.mhrsproject.entities.AppointmentSlot;
import com.bil372.mhrsproject.entities.Doctor;
import com.bil372.mhrsproject.entities.Patient;
import com.bil372.mhrsproject.entities.Prescription;
import com.bil372.mhrsproject.repositories.PrescriptionsRepository;

@Service
public class PrescriptionsService {
    private final PrescriptionsRepository prescriptionsRepository;

    public PrescriptionsService(PrescriptionsRepository prescriptionsRepository){
        this.prescriptionsRepository = prescriptionsRepository;
    }

    public List<Prescription> getPatientPrescriptions(Patient patient){
        return prescriptionsRepository.findByPatient(patient);
    }

    public List<Prescription> getPatientPrescriptionsByNationalId(long patientNationalId){
        return prescriptionsRepository.findByPatient_PatientNationalId(patientNationalId);
    }

    public List<Prescription> getDoctorPrescriptions(Doctor doctor){
        return prescriptionsRepository.findByDoctor(doctor);
    }

    public List<Prescription> getDoctorPrescriptionsByNationalId(long doctorNationalId){
        return prescriptionsRepository.findByDoctor_DoctorNationalId(doctorNationalId);
    }

    public List<Prescription> findByAppointment(AppointmentSlot appointmentSlot){
        List<Prescription> prescriptions = prescriptionsRepository.findByAppointmentSlot(appointmentSlot);
        return prescriptions;
    }

    public List<Prescription> findByAppointmentId(int id){
        return prescriptionsRepository.findByAppointmentSlot_AppointmentId(id);
    }
}
