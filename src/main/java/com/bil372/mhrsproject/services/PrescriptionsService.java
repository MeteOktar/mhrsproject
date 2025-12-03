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

    public PrescriptionsService(PrescriptionsRepository prescriptionsRepository) {
        this.prescriptionsRepository = prescriptionsRepository;
    }

    public List<Prescription> getPatientPrescriptions(Patient patient) {
        return prescriptionsRepository.findByPatient(patient);
    }

    public List<Prescription> getPatientPrescriptionsByNationalId(long patientNationalId) {
        return prescriptionsRepository.findByPatient_PatientNationalId(patientNationalId);
    }

    public List<Prescription> getDoctorPrescriptions(Doctor doctor) {
        return prescriptionsRepository.findByDoctor(doctor);
    }

    public List<Prescription> getDoctorPrescriptionsByNationalId(long doctorNationalId) {
        return prescriptionsRepository.findByDoctor_DoctorNationalId(doctorNationalId);
    }

    public List<Prescription> findByAppointment(AppointmentSlot appointmentSlot) {
        List<Prescription> prescriptions = prescriptionsRepository.findByAppointmentSlot(appointmentSlot);
        return prescriptions;
    }

    public List<Prescription> findByAppointmentId(int id) {
        return prescriptionsRepository.findByAppointmentSlot_AppointmentId(id);
    }

    // Admin Methods
    public List<com.bil372.mhrsproject.DTOs.AdminPrescriptionDTO> getAllPrescriptions(int limit) {
        org.springframework.data.domain.Pageable pageable = org.springframework.data.domain.PageRequest.of(0, limit);
        return prescriptionsRepository.findAll(pageable).getContent().stream()
                .map(p -> new com.bil372.mhrsproject.DTOs.AdminPrescriptionDTO(
                        p.getPrescriptionId(),
                        p.getPrescriptionDateTime(),
                        p.getDoctor() != null ? p.getDoctor().getFirstName() + " " + p.getDoctor().getLastName()
                                : "N/A",
                        p.getPatient() != null ? p.getPatient().getFirstName() + " " + p.getPatient().getLastName()
                                : "N/A",
                        p.getHospital() != null ? p.getHospital().getName() : "N/A",
                        p.getDepartment() != null ? p.getDepartment().getBranchName() : "N/A",
                        p.getDiagnosis(),
                        p.getNotes(),
                        p.getPrescribedDrugs() != null
                                ? p.getPrescribedDrugs().stream().map(d -> d.getDrugName() + " (" + d.getDosage() + ")")
                                        .toList()
                                : java.util.Collections.emptyList()))
                .toList();
    }
}
