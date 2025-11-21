package com.bil372.mhrsproject.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bil372.mhrsproject.entities.WaitingList;

public interface WaitingListRepository extends JpaRepository<WaitingList,Integer>{

    List<WaitingList> findBydoctorNationalId(long doctorNationalId);

    List<WaitingList> findBypatientNationalId(long patientNationalId);
}
