package com.bil372.mhrsproject.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bil372.mhrsproject.entities.Doctor;
import com.bil372.mhrsproject.entities.Patient;
import com.bil372.mhrsproject.entities.WaitingList;

public interface WaitingListRepository extends JpaRepository<WaitingList,Integer>{

    List<WaitingList> findByDoctor(Doctor doctor);

    List<WaitingList> findByPatient(Patient patient); //bir hasta 1 waiting listte olabilecegi icin tek döner

}
