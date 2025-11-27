package com.bil372.mhrsproject.controllers;

import org.springframework.web.bind.annotation.*;

import com.bil372.mhrsproject.entities.Hospital;
import com.bil372.mhrsproject.services.HospitalService;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;




@RestController //rest api oldugunu ve json donecegini belirtir
@RequestMapping("/api/hospital")
public class HospitalController {

    private final HospitalService hospitalService;

    public HospitalController(HospitalService hospitalService){
        this.hospitalService = hospitalService;
    }

    @GetMapping("/search")
    public List<Hospital> search(@RequestParam String city, @RequestParam(required = false) String district) {
        
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
    
    
    
}
