package com.bil372.mhrsproject.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bil372.mhrsproject.entities.Hospital;

public interface HospitalRepository extends JpaRepository<Hospital, Integer> {

    //hastaneyi id ile bulmak
    Optional<Hospital> findByHospitalId(int hospitalId);

    //o sehirdeki tum hastaneler
    List<Hospital> findByCity(String city);

    //o sehirdeki ve ilcedeki tum hastaneler
    List<Hospital> findByCityAndDistrict(String city, String district);

    Hospital findFirstByHospitalDoctors_DoctorNationalId(long doctorNationalId);

    Hospital findFirstByHospitalDepartments_DepartmentId(int departmentId);

    @Query("SELECT DISTINCT h.city FROM Hospital h ORDER BY h.city ASC")
    List<String> findAllCities();

    @Query("SELECT DISTINCT h.district FROM Hospital h WHERE h.city = :city ORDER BY h.district ASC")
    List<String> findAllDistrictsInCity(@Param("city") String city);

}