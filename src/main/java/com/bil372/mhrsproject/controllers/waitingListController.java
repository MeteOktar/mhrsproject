package com.bil372.mhrsproject.controllers;

import com.bil372.mhrsproject.DTOs.WaitingListDTO;
import com.bil372.mhrsproject.services.WaitingListService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;

@RestController //rest api oldugunu ve json donecegini belirtir
@RequestMapping("/api/waiting-list") //donecek her seyin urlsi /api/waiting-list/... olur
public class WaitingListController {
    private final WaitingListService waitingListService;

    public WaitingListController(WaitingListService waitingListService){
        this.waitingListService = waitingListService;
    }

    @GetMapping("/doctor/{doctorNationalId}") //GET istegi httpden
    public List<WaitingListDTO> getDoctorWaitingList(@PathVariable long doctorNationalId) {
        return waitingListService.getDoctorWaitingList(doctorNationalId);
    }
    
    @GetMapping("/patient/{patientNationalId}")
    public List<WaitingListDTO> getPatientWaitingList(@PathVariable long patientNationalId) {
        return waitingListService.getPatientWaitingList(patientNationalId);
    }

    @GetMapping("/admin/")
    public List<WaitingListDTO> getAdminWaitingList(){
        return waitingListService.getAdminWaitingList();
    }
    
}
