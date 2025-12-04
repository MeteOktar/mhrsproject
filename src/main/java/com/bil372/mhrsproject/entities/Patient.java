package com.bil372.mhrsproject.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Table(name = "patients")
@Entity
public class Patient {
    @Id
    @Column(name = "nationalId")
    private long patientNationalId;

    @Column(name = "passwordHash", nullable = false)
    private String passwordHash;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "heightCm")
    private int heightCm;

    @Column(name = "weightKg")
    private int weightKg;

    @Column(name = "bloodType")
    private String bloodType;

    @OneToMany(mappedBy = "patient", fetch = FetchType.LAZY, cascade = jakarta.persistence.CascadeType.ALL)
    @JsonIgnore
    private List<WaitingList> patientWaitingLists;

    @OneToMany(mappedBy = "patient", fetch = FetchType.LAZY, cascade = jakarta.persistence.CascadeType.ALL)
    @JsonIgnore
    private List<AppointmentSlot> pAppointmentSlots;

    @OneToMany(mappedBy = "patient", fetch = FetchType.LAZY, cascade = jakarta.persistence.CascadeType.ALL)
    @JsonIgnore
    private List<Prescription> patientPrescriptions;

}
