package com.bil372.mhrsproject.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bil372.mhrsproject.entities.Doctor;
import com.bil372.mhrsproject.entities.Hospital;
import com.bil372.mhrsproject.entities.HospitalDepartment;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    //doctoru id ile bulmak
    Optional<Doctor> findByDoctorNationalId(long doctorNationalId);

    // Hastaneye göre doktor listesi
    List<Doctor> findByHospital(Hospital hospital);

    // Departmana göre doktor listesi
    List<Doctor> findByHospitalAndDepartment(Hospital hospital,HospitalDepartment department);

    List<Doctor> findByDepartment_DepartmentId(int departmentId);
}

