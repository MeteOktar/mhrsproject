package com.bil372.mhrsproject.controllers;

import com.bil372.mhrsproject.entities.WaitingList;
import com.bil372.mhrsproject.services.WaitingListService;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController //rest api oldugunu ve json donecegini belirtir
@RequestMapping("/api/waiting-list") //donecek her seyin urlsi /api/waiting-list/... olur
public class WaitingListController {
    private final WaitingListService waitingListService;

    public WaitingListController(WaitingListService waitingListService){
        this.waitingListService = waitingListService;
    }

    @GetMapping("/doctor/{doctorNationalId}") //GET istegi httpden
    public List<WaitingList> getDoctorWaitingList(@PathVariable long doctorNationalId) {
        return waitingListService.getDoctorWaitingList(doctorNationalId);
    }
    
    @GetMapping("/patient/{patientNationalId}")
    public List<WaitingList> getPatientWaitingList(@PathVariable long patientNationalId) {
        return waitingListService.getPatientWaitingList(patientNationalId);
    }
}
