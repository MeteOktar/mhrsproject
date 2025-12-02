package com.bil372.mhrsproject.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bil372.mhrsproject.entities.AppointmentSlot;
import com.bil372.mhrsproject.entities.Hospital;
import com.bil372.mhrsproject.entities.HospitalDepartment;
import com.bil372.mhrsproject.entities.Patient;
import com.bil372.mhrsproject.repositories.AppointmentSlotsRepository;
import com.bil372.mhrsproject.repositories.PatientRepository;

import jakarta.transaction.Transactional;

@Service
public class AppointmentSlotsService {
    
    private final AppointmentSlotsRepository aSlotsRepository;
    private final PatientRepository patientRepository;

    public AppointmentSlotsService(AppointmentSlotsRepository aSlotsRepository, PatientRepository patientRepository){
        this.aSlotsRepository = aSlotsRepository;
        this.patientRepository = patientRepository;
    }

    public List<AppointmentSlot> getSlotsByDoctorAndDate(long doctorNationalId, LocalDate date) {
    LocalDateTime startOfDay = date.atStartOfDay();
    LocalDateTime endOfDay = date.atTime(LocalTime.MAX);

    return aSlotsRepository
            .findByDoctor_DoctorNationalIdAndSlotDateTimeBetweenAndStatusIn(
                    doctorNationalId,
                    startOfDay,
                    endOfDay,
                    List.of("empty", "booked","cancelled","CANCELLED_BY_DOCTOR","CANCELLED_BY_PATIENT")
            );
    }


    public List<AppointmentSlot> getDoctorPastAppointmentSlots(long doctorNationalId){
        List<AppointmentSlot> aSlots = aSlotsRepository.findByDoctor_DoctorNationalIdAndSlotDateTimeBefore(doctorNationalId, LocalDateTime.now());
        return aSlots;
    }
    
    public List<AppointmentSlot> getDoctorFutureAppointmentSlots(long doctorNationalId){
        List<AppointmentSlot> aSlots = aSlotsRepository.findByDoctor_DoctorNationalIdAndSlotDateTimeAfter(doctorNationalId, LocalDateTime.now());
        return aSlots;
    }

    public List<AppointmentSlot> getPatientPastAppointmentSlots(long patientNationalId){
        List<AppointmentSlot> aSlots = aSlotsRepository.findByPatient_PatientNationalIdAndSlotDateTimeBefore(patientNationalId, LocalDateTime.now());
        return aSlots;
    }

    public List<AppointmentSlot> getPatientFutureAppointmentSlots(long patientNationalId){
        List<AppointmentSlot> aSlots = aSlotsRepository.findByPatient_PatientNationalIdAndSlotDateTimeAfter(patientNationalId, LocalDateTime.now());
        return aSlots;
    }

    public List<AppointmentSlot> getHospitalAndDepartmentSlots(Hospital hospital, HospitalDepartment hospitalDepartment){
        List<AppointmentSlot> aSlots = aSlotsRepository.findByHospitalAndDepartment(hospital, hospitalDepartment);
        return aSlots;
    }

    public List<AppointmentSlot> getHospitalAndDepartmentAndSlotDateTime(Hospital hospital,  HospitalDepartment hospitalDepartment, LocalDateTime dateTime){
        List<AppointmentSlot> aSlots = aSlotsRepository.findByHospitalAndDepartmentAndSlotDateTime(hospital, hospitalDepartment, dateTime);
        return aSlots;
    }

    public void cancelAppointmentByDoctor(int appointmentId) {
        AppointmentSlot slot = aSlotsRepository
                .findById(appointmentId)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));

        slot.setStatus("CANCELLED_BY_DOCTOR"); 
        aSlotsRepository.save(slot);
    }

    public void cancelAppointmentByPatient(int appointmentId){
        AppointmentSlot slot = aSlotsRepository
                                .findById(appointmentId)
                                .orElseThrow(() -> new RuntimeException("Appointment not found"));

        slot.setStatus("CANCELLED_BY_PATIENT");
        aSlotsRepository.save(slot);
    }

    @Transactional
    public void bookAppointment(int appointmentId, long patientNationalId) {
        AppointmentSlot slot = aSlotsRepository.findById(appointmentId)
                .orElseThrow(() -> new RuntimeException("Appointment slot not found"));

        // Dolmuş slotu tekrar alma
        if (slot.getStatus().equals("booked")) {
            throw new RuntimeException("Appointment slot is not available");
        }

        Patient patient = patientRepository.findByPatientNationalId(patientNationalId)
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        slot.setPatient(patient); 
        slot.setStatus("booked");

        aSlotsRepository.save(slot);
    }

}
