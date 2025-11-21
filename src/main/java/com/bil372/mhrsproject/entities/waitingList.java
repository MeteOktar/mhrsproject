package com.bil372.mhrsproject.entities;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Table(name= "waiting_list")
@Entity
public class WaitingList {
    @Id
    @Column(name = "waitingId")
    private int waitingId;

    @Column(name = "level")
    private String level;

    @Column(name = "doctorNationalId")
    private long doctorNationalId;

    @Column(name = "hospitalId")
    private int hospitalId;

    @Column(name = "departmentId")
    private int departmentId;

    @Column(name = "patientNationalId")
    private long patientNationalId;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime requestDateTime;

}
