package com.bil372.mhrsproject.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bil372.mhrsproject.DTOs.CreateHospitalRequest;
import com.bil372.mhrsproject.entities.Hospital;
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

    public HospitalService(DoctorRepository doctorRepository,
                           PatientRepository patientRepository,
                           HospitalDepartmentRepository departmentRepository,
                           HospitalRepository hospitalRepository) {
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

    public Hospital createHospital(CreateHospitalRequest req) {
        Integer maxId = hospitalRepository.findMaxHospitalId();
        int newId = (maxId == null ? 1 : maxId + 1);

        Hospital h = new Hospital();
        h.setHospitalId(newId);
        h.setName(req.getName());
        h.setCity(req.getCity());

        // null gelirse boş string atıyoruz, NOT NULL column vs. sorun çıkmasın
        h.setDistrict(req.getDistrict() != null ? req.getDistrict() : "");
        h.setStreetAddress(req.getStreetAddress() != null ? req.getStreetAddress() : "");
        h.setPhoneNumber(req.getPhoneNumber() != null ? req.getPhoneNumber() : "");

        return hospitalRepository.save(h);
    }
}
