package com.bil372.mhrsproject.controllers;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bil372.mhrsproject.DTOs.WaitingListDTO;
import com.bil372.mhrsproject.services.WaitingListService;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/api/waiting-list")
@RequiredArgsConstructor
public class WaitingListController {

    private final WaitingListService waitingListService;

    // Hastanın kendi bekleme listesini çekmesi
    @GetMapping("/patient")
    public List<WaitingListDTO> getMyWaitingList(Authentication authentication) {
        String username = authentication.getName();
        long patientNationalId = Long.parseLong(username);
        return waitingListService.getPatientWaitingList(patientNationalId);
    }

    @PostMapping("/doctor/{doctorId}")
    public ResponseEntity<?> joinDoctorWaitingList(
            @PathVariable long doctorId,
            Authentication authentication
    ) {
        long patientNationalId = Long.parseLong(authentication.getName());
        waitingListService.joinDoctorWaitingList(patientNationalId, doctorId);
        return ResponseEntity.ok().build();
    }

    // Hastanın departman bekleme listesine kaydı
    @PostMapping("/department/{departmentId}")
    public ResponseEntity<?> joinDepartmentWaitingList(
            @PathVariable int departmentId,
            Authentication authentication
    ) {
        long patientNationalId = Long.parseLong(authentication.getName());
        waitingListService.joinDepartmentWaitingList(patientNationalId, departmentId);
        return ResponseEntity.ok().build();
    }
}
