package com.bil372.mhrsproject.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bil372.mhrsproject.entities.waitingList;

public interface waitingListRepository extends JpaRepository<waitingList,Integer>{

    List<waitingList> findBydoctorNationalId(String doctorNationalId);

    List<waitingList> findBypatientNationalId(String patientNationalId);
}
