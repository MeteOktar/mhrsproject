package com.bil372.mhrsproject.entities;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Table(name = "prescriptions")
@Entity
public class Prescription {

    @Id
    @Column(name = "prescriptionId")
    private int prescriptionId;

    @ManyToOne
    @JoinColumn(name="appointmentId")
    private AppointmentSlot appointment;

    @ManyToOne
    @JoinColumn(name = "doctorNationalId")
    private Doctor doctor;
    
    @ManyToOne
    @JoinColumn(name = "patientNationalId")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "hospitalId")
    private Hospital hospital;

    @ManyToOne
    @JoinColumn(name = "departmentId")
    private HospitalDepartment department;

    @OneToMany(mappedBy = "prescription",fetch = FetchType.LAZY)
    @JsonIgnore
    private List<PrescriptionDrugs> prescribedDrugs;

    @Column(name = "prescriptionDateTime")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime prescriptionDateTime;

    @Column(name = "diagnosis")
    private String diagnosis;

    @Column(name = "notes")
    private String notes;
}
