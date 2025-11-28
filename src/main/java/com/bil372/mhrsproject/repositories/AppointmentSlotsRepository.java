package com.bil372.mhrsproject.repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bil372.mhrsproject.entities.AppointmentSlot;
import com.bil372.mhrsproject.entities.Doctor;
import com.bil372.mhrsproject.entities.Patient;

public interface AppointmentSlotsRepository extends JpaRepository<AppointmentSlot, Integer> {

    AppointmentSlot findByAppointmentId(Integer appointmentId);
    
    List<AppointmentSlot> findByDoctor(Doctor doctor);

    List<AppointmentSlot> findByDoctorNationalId(long doctorNationalId);

    List<AppointmentSlot> findByPatient(Patient patient);

    List<AppointmentSlot> findByPatientNationalId(long patientNationalId);

    List<AppointmentSlot> findByHospitalIdAndDepartmentId(int hospitalId, int departmentId);

    List<AppointmentSlot> findByHospitalIdAndDepartmentIdAndSlotDateTime(int hospitalId, int departmentId, LocalDateTime slotDateTime);
    
}
