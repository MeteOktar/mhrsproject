package com.bil372.mhrsproject.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bil372.mhrsproject.entities.AppointmentSlot;
import com.bil372.mhrsproject.entities.Hospital;
import com.bil372.mhrsproject.entities.HospitalDepartment;
import com.bil372.mhrsproject.repositories.AppointmentSlotsRepository;

@Service
public class AppointmentSlotsService {
    
    private final AppointmentSlotsRepository aSlotsRepository;

    public AppointmentSlotsService(AppointmentSlotsRepository aSlotsRepository){
        this.aSlotsRepository = aSlotsRepository;
    }

    public List<AppointmentSlot> getDoctorAppointmentSlots(long doctorNationalId){
        List<AppointmentSlot> aSlots = aSlotsRepository.findByDoctorNationalId(doctorNationalId);
        return aSlots;
    }
    
    public List<AppointmentSlot> getPatientAppointmentSlots(long patientNationalId){
        List<AppointmentSlot> aSlots = aSlotsRepository.findByPatientNationalId(patientNationalId);
        return aSlots;
    }

    public List<AppointmentSlot> getHospitalAndDepartmentSlots(Hospital hospital, HospitalDepartment hospitalDepartment){
        List<AppointmentSlot> aSlots = aSlotsRepository.findByHospitalIdAndDepartmentId(hospital.getHospitalId(), hospitalDepartment.getDepartmentId());
        return aSlots;
    }

    public List<AppointmentSlot> getHospitalAndDepartmentAndSlotDateTime(Hospital hospital,  HospitalDepartment hospitalDepartment, LocalDateTime dateTime){
        List<AppointmentSlot> aSlots = aSlotsRepository.findByHospitalIdAndDepartmentIdAndSlotDateTime(hospital.getHospitalId(), hospitalDepartment.getDepartmentId(), dateTime);
        return aSlots;
    }
}
