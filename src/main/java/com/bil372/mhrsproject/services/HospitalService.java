package com.bil372.mhrsproject.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bil372.mhrsproject.DTOs.WaitingListDTO;
import com.bil372.mhrsproject.entities.Doctor;
import com.bil372.mhrsproject.entities.Hospital;
import com.bil372.mhrsproject.entities.WaitingList;
import com.bil372.mhrsproject.repositories.DoctorRepository;
import com.bil372.mhrsproject.repositories.HospitalDepartmentRepository;
import com.bil372.mhrsproject.repositories.HospitalRepository;
import com.bil372.mhrsproject.repositories.PatientRepository;

@Service
public class HospitalService {

    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;
    private final HospitalDepartmentRepository departmentRepository;
    private final HospitalRepository hospitalRepository;

    public HospitalService(DoctorRepository doctorRepository, PatientRepository patientRepository, HospitalDepartmentRepository departmentRepository, HospitalRepository hospitalRepository){
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
        this.departmentRepository = departmentRepository;
        this.hospitalRepository = hospitalRepository;
    }


    public List<Hospital> getHospitalsByLocation(String city, String district) {
        if( district == null || district.isBlank() ){ //district yok
            return hospitalRepository.findByCity(city);
        }
        else{
            return hospitalRepository.findByCityAndDistrict(city, district);
        }
    }
    public List<String> getAllCities(){
        return hospitalRepository.findAllCities();
    }

    public List<String> getAllDistrictsInCity(String city){
        return hospitalRepository.findAllDistrictsInCity(city);
    }
}
