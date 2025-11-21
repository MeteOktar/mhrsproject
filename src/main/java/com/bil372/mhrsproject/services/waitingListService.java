package com.bil372.mhrsproject.services;

import com.bil372.mhrsproject.entities.WaitingList;
import com.bil372.mhrsproject.repositories.WaitingListRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WaitingListService {
    //buyuk harfli W olan obje, digeri class
    private final WaitingListRepository WaitingListRepository;

    public WaitingListService(WaitingListRepository waitingListRepository){
        this.WaitingListRepository = waitingListRepository;
    }

    public List<WaitingList> getDoctorWaitingList(long doctorNationalId){
        System.out.println("Doctor ID = " + doctorNationalId);
        List<WaitingList> result = WaitingListRepository.findBydoctorNationalId(doctorNationalId);
        System.out.println("Result size = " + (result == null ? "null" : result.size()));
        return result;
    }

    public List<WaitingList> getPatientWaitingList(long patientNationalId){
        return WaitingListRepository.findBypatientNationalId(patientNationalId);
    }

}
