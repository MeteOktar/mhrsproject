package com.bil372.mhrsproject.repositories;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bil372.mhrsproject.entities.AppointmentSlot;
import com.bil372.mhrsproject.entities.Doctor;
import com.bil372.mhrsproject.entities.Hospital;
import com.bil372.mhrsproject.entities.HospitalDepartment;
import com.bil372.mhrsproject.entities.Patient;

public interface AppointmentSlotsRepository extends JpaRepository<AppointmentSlot, Integer> {

    AppointmentSlot findByAppointmentId(Integer appointmentId);
    
    List<AppointmentSlot> findByDoctor(Doctor doctor);

    List<AppointmentSlot> findByDoctor_DoctorNationalId(long doctorNationalId);

    List<AppointmentSlot> findByDoctor_DoctorNationalIdAndSlotDateTimeBefore(
        long doctorNationalId,
        LocalDateTime before
    );

    List<AppointmentSlot> findByDoctor_DoctorNationalIdAndSlotDateTimeAfter(
        long doctorNationalId,
        LocalDateTime after
    );
    
    List<AppointmentSlot> findByPatient(Patient patient);

    List<AppointmentSlot> findByPatient_PatientNationalId(long patientNationalId);

    long countByStatus(String status);

    List<AppointmentSlot> findByHospitalAndDepartment(
    Hospital hospital,
    HospitalDepartment department
    );

    List<AppointmentSlot> findByHospitalAndDepartmentAndSlotDateTime(
    Hospital hospital,
    HospitalDepartment department,
    LocalDateTime slotDateTime
    );

    List<AppointmentSlot> findByPatient_PatientNationalIdAndSlotDateTimeBefore(long patientNationalId,
            LocalDateTime now);

    List<AppointmentSlot> findByPatient_PatientNationalIdAndSlotDateTimeAfter(long patientNationalId,
            LocalDateTime now);

    List<AppointmentSlot> findByDoctor_DoctorNationalIdAndSlotDateTimeBetweenAndStatusIn(
        long doctorNationalId,
        LocalDateTime start,
        LocalDateTime end,
        List<String> statuses
    );


}
