package com.bil372.mhrsproject.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.bil372.mhrsproject.DTOs.CreateHospitalDepartmentRequest;
import com.bil372.mhrsproject.DTOs.HospitalDepartmentDTO;
import com.bil372.mhrsproject.entities.Hospital;
import com.bil372.mhrsproject.entities.HospitalDepartment;
import com.bil372.mhrsproject.repositories.HospitalDepartmentRepository;
import com.bil372.mhrsproject.repositories.HospitalRepository;

@RestController
@RequestMapping("/api/department")
public class HospitalDepartmentController {

    private final HospitalDepartmentRepository departmentRepository;
    private final HospitalRepository hospitalRepository;

    public HospitalDepartmentController(HospitalDepartmentRepository departmentRepository,
                                        HospitalRepository hospitalRepository) {
        this.departmentRepository = departmentRepository;
        this.hospitalRepository = hospitalRepository;
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

    @PostMapping
    public HospitalDepartmentDTO createDepartment(@RequestBody CreateHospitalDepartmentRequest req) {
        Hospital hospital = hospitalRepository.findByHospitalId(req.getHospitalId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Belirtilen ID'ye ait hastane bulunamadı."));

        Integer maxId = departmentRepository.findMaxDepartmentId();
        int newId = (maxId == null ? 1 : maxId + 1);

        HospitalDepartment d = new HospitalDepartment();
        d.setDepartmentId(newId);
        d.setBranchName(req.getBranchName());
        d.setHospital(hospital);

        HospitalDepartment saved = departmentRepository.save(d);

        return new HospitalDepartmentDTO(saved.getDepartmentId(), saved.getBranchName());
    }

    // Departman silme
    @DeleteMapping("/{departmentId}")
    public void deleteDepartment(@PathVariable int departmentId) {
        HospitalDepartment dept = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Bu ID ile departman bulunamadı."
                ));

        departmentRepository.delete(dept);
    }
}
