package com.bil372.mhrsproject.controllers;

import com.bil372.mhrsproject.entities.waitingList;
import com.bil372.mhrsproject.services.waitingListService;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController //rest api oldugunu ve json donecegini belirtir
@RequestMapping("/api/waiting-list") //donecek her seyin urlsi /api/waiting-list/... olur
public class waitingListController {
    private final waitingListService waitingListService;

    public waitingListController(waitingListService waitingListService){
        this.waitingListService = waitingListService;
    }

    @GetMapping("/doctor/{doctorNationalId}") //GET istegi httpden
    public List<waitingList> getDoctorWaitingList(@PathVariable long doctorNationalId) {
        return waitingListService.getDoctorWaitingList(doctorNationalId);
    }
    
    @GetMapping("/patient/{patientNationalId}")
    public List<waitingList> getPatientWaitingList(@PathVariable long patientNationalId) {
        return waitingListService.getPatientWaitingList(patientNationalId);
    }
}
