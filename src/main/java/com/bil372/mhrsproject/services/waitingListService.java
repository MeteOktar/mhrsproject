package com.bil372.mhrsproject.services;

import com.bil372.mhrsproject.DTOs.WaitingListDTO;
import com.bil372.mhrsproject.entities.Doctor;
import com.bil372.mhrsproject.entities.Patient;
import com.bil372.mhrsproject.entities.WaitingList;
import com.bil372.mhrsproject.repositories.DoctorRepository;
import com.bil372.mhrsproject.repositories.PatientRepository;
import com.bil372.mhrsproject.repositories.WaitingListRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WaitingListService {

    private final WaitingListRepository waitingListRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;

    public WaitingListService(WaitingListRepository waitingListRepository, DoctorRepository doctorRepository, PatientRepository patientRepository){
        this.waitingListRepository = waitingListRepository;
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
    }

    public List<WaitingListDTO> getDoctorWaitingList(long doctorNationalId){
        Doctor doctor = doctorRepository.findByDoctorNationalId(doctorNationalId).orElseThrow(() -> new RuntimeException("Doctor not found"));
        List<WaitingList> result = waitingListRepository.findByDoctor(doctor);
        System.out.println("Result size = " + (result == null ? "null" : result.size()));
        return result.stream().map(this::waitinglistDTOMapper).toList();
    }

    public List<WaitingListDTO> getPatientWaitingList(long patientNationalId){
        Patient patient = patientRepository.findByPatientNationalId(patientNationalId).orElseThrow(() -> new RuntimeException("Patient not found"));
        List<WaitingList> result = waitingListRepository.findByPatient(patient);
        return result.stream().map(this::waitinglistDTOMapper).toList();
    }

    public List<WaitingListDTO> getAdminWaitingList() {
        List<WaitingList> result = waitingListRepository.findAll();
        return result.stream().map(this::waitinglistDTOMapper).toList();
    }

    private WaitingListDTO waitinglistDTOMapper(WaitingList w){

        String doctorName = null;
        if (w.getDoctor() != null) {
            doctorName = w.getDoctor().getFirstName() + " " + w.getDoctor().getLastName();
        }

        String hospitalName = w.getHospital() != null ? w.getHospital().getName() : null;
        String departmentName = w.getDepartment() != null ? w.getDepartment().getBranchName() : null;

        String patientName = null;
        if (w.getPatient() != null) {
            patientName = w.getPatient().getFirstName() + " " + w.getPatient().getLastName();
        }

        return new WaitingListDTO(
                w.getWaitingId(),
                w.getLevel(),
                doctorName,
                hospitalName,
                departmentName,
                patientName,
                w.getRequestDateTime()
        );
    }

}
