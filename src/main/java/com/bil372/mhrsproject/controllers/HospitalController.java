package com.bil372.mhrsproject.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bil372.mhrsproject.DTOs.CreateHospitalRequest;
import com.bil372.mhrsproject.entities.Hospital;
import com.bil372.mhrsproject.services.HospitalService;

@RestController // rest api oldugunu ve json donecegini belirtir
@RequestMapping("/api/hospital")
public class HospitalController {

    private final HospitalService hospitalService;

    public HospitalController(HospitalService hospitalService){
        this.hospitalService = hospitalService;
    }

    // Admin sayfası için: tüm hastaneler
    @GetMapping("/all")
    public List<Hospital> getAllHospitals() {
        return hospitalService.getAllHospitals();
    }

    @GetMapping("/search")
    public List<Hospital> search(@RequestParam String city,
                                 @RequestParam(required = false) String district) {

        return hospitalService.getHospitalsByLocation(city, district);
    }

    @GetMapping("/cities")
    public List<String> getAllCities(){
        return hospitalService.getAllCities();
    }

    @GetMapping("/cities/{city}/districts")
    public List<String> getDistrictsInCity(@PathVariable String city) {
        return hospitalService.getAllDistrictsInCity(city);
    }

    // Yeni hastane ekleme (Admin)
    @PostMapping
    public Hospital createHospital(@RequestBody CreateHospitalRequest req) {
        return hospitalService.createHospital(req);
    }
}
