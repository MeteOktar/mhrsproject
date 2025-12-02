package com.bil372.mhrsproject.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bil372.mhrsproject.entities.Doctor;
import com.bil372.mhrsproject.entities.Patient;
import com.bil372.mhrsproject.entities.WaitingList;

public interface WaitingListRepository extends JpaRepository<WaitingList,Integer>{

    List<WaitingList> findByDoctor(Doctor doctor);

    List<WaitingList> findByPatient(Patient patient); //bir hasta 1 waiting listte olabilecegi icin tek döner

    Optional<WaitingList> findByWaitingId(int waitingId);

    boolean existsByPatient_PatientNationalIdAndDoctor_DoctorNationalIdAndLevel(
        long patientNationalId,
        long doctorNationalId,
        String level
    );
    boolean existsByPatient_PatientNationalIdAndHospital_HospitalIdAndDepartment_DepartmentIdAndLevel(
        long patientNationalId,
        int hospitalId,
        int departmentId,
        String level
    );
}
