package com.bil372.mhrsproject.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bil372.mhrsproject.DTOs.HospitalDepartmentDTO;
import com.bil372.mhrsproject.repositories.HospitalDepartmentRepository;

@RestController
@RequestMapping("/api/department")
public class HospitalDepartmentController {

    private final HospitalDepartmentRepository departmentRepository;

    public HospitalDepartmentController(HospitalDepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

   @GetMapping("/byHospital")
    public List<HospitalDepartmentDTO> getDepartmentsByHospital(@RequestParam int hospitalId) {
        return departmentRepository.findByHospital_HospitalId(hospitalId)
                .stream()
                .map(d -> new HospitalDepartmentDTO(
                        d.getDepartmentId(),
                        d.getBranchName()
                ))
                .toList();
    }
}
