package com.bil372.mhrsproject.entities;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;

@Data
@Table(name= "waiting_list",
    uniqueConstraints = {
        @UniqueConstraint(
            name = "uq_waiting_patient_doctor",
            columnNames = { "patientNationalId", "doctorNationalId", "level","hospitalId", "departmentId" }
        )
    }
)
@Entity
public class WaitingList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "waitingId")
    private Integer waitingId;

    @Column(name = "level")
    private String level;

    @ManyToOne
    @JoinColumn(name = "doctorNationalId")
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "hospitalId")
    private Hospital hospital;

    @ManyToOne
    @JoinColumn(name = "departmentId")
    private HospitalDepartment department;

    @ManyToOne
    @JoinColumn(name = "patientNationalId")
    private Patient patient;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime requestDateTime;
}
