package com.bil372.mhrsproject.controllers;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bil372.mhrsproject.DTOs.WaitingListDTO;
import com.bil372.mhrsproject.services.WaitingListService;

@RestController
@RequestMapping("/api/doctor")
public class DoctorController {

    private final WaitingListService waitingListService;

    public DoctorController(WaitingListService waitingListService) {
        this.waitingListService = waitingListService;
    }

    @GetMapping("/waiting-list")
    public List<WaitingListDTO> getMyWaitingList(Authentication authentication) {
        String username = authentication.getName();           // nationalId
        long doctorNationalId = Long.parseLong(username);     // longa dondu
        return waitingListService.getDoctorWaitingList(doctorNationalId);
    }
}

