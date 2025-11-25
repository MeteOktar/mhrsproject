package com.bil372.mhrsproject.controllers;

import org.springframework.web.bind.annotation.*;

import com.bil372.mhrsproject.entities.Hospital;
import com.bil372.mhrsproject.services.HospitalService;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;




@RestController //rest api oldugunu ve json donecegini belirtir
@RequestMapping("/api/hospital") //donecek her seyin urlsi /api/waiting-list/... olur
public class HospitalController {

    private final HospitalService hospitalService;

    public HospitalController(HospitalService hospitalService){
        this.hospitalService = hospitalService;
    }

    @GetMapping("/byCity/{city}") //get istegi httpden
    public List<Hospital> getHospitalByName(@PathVariable String city) {
        return hospitalService.getHospitalsByCity(city);
    }
    
}
