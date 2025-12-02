package com.bil372.mhrsproject.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bil372.mhrsproject.entities.Hospital;
import com.bil372.mhrsproject.entities.HospitalDepartment;

public interface HospitalDepartmentRepository extends JpaRepository<HospitalDepartment, Integer> {

    List<HospitalDepartment> findByHospital(Hospital hospital);

    HospitalDepartment findByBranchName(String branchName);

    List<HospitalDepartment> findByHospital_HospitalId(int hospitalId);

    HospitalDepartment findFirstByDepartmentDoctors_DoctorNationalId(long doctorNationalId);
}
