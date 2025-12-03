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

    public HospitalService(DoctorRepository doctorRepository, PatientRepository patientRepository,
            HospitalDepartmentRepository departmentRepository, HospitalRepository hospitalRepository) {
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
        this.departmentRepository = departmentRepository;
        this.hospitalRepository = hospitalRepository;
    }

    public List<Hospital> getHospitalsByLocation(String city, String district) {
        if (district == null || district.isBlank()) { // district yok
            return hospitalRepository.findByCity(city);
        } else {
            return hospitalRepository.findByCityAndDistrict(city, district);
        }
    }

    public List<String> getAllCities() {
        return hospitalRepository.findAllCities();
    }

    public List<String> getAllDistrictsInCity(String city) {
        return hospitalRepository.findAllDistrictsInCity(city);
    }

    public List<Hospital> getAllHospitals() {
        return hospitalRepository.findAll();
    }

    // Admin Methods
    public List<com.bil372.mhrsproject.DTOs.AdminHospitalDTO> getAllHospitalsAsDTO() {
        return hospitalRepository.findAll().stream()
                .map(h -> new com.bil372.mhrsproject.DTOs.AdminHospitalDTO(
                        String.valueOf(h.getHospitalId()),
                        h.getName(),
                        h.getCity()))
                .toList();
    }

    public List<com.bil372.mhrsproject.DTOs.AdminDepartmentDTO> getAllDepartmentsAsDTO() {
        return departmentRepository.findAll().stream()
                .map(d -> new com.bil372.mhrsproject.DTOs.AdminDepartmentDTO(
                        String.valueOf(d.getDepartmentId()),
                        d.getBranchName(),
                        String.valueOf(d.getHospital().getHospitalId())))
                .toList();
    }
}
