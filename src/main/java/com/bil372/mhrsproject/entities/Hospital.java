package com.bil372.mhrsproject.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Table(name = "hospitals")
@Entity
public class Hospital {
    @OneToMany(mappedBy = "hospital", fetch = FetchType.LAZY,
    cascade = CascadeType.ALL,
    orphanRemoval = true)
    @JsonIgnore
    private List<HospitalDepartment> hospitalDepartments;

    @OneToMany(mappedBy = "hospital", fetch = FetchType.LAZY,
    cascade = CascadeType.ALL,
    orphanRemoval = true)
    @JsonIgnore
    private List<Doctor> hospitalDoctors;

    @OneToMany(mappedBy = "hospital", fetch = FetchType.LAZY,
    cascade = CascadeType.ALL,
    orphanRemoval = true)
    @JsonIgnore
    private List<WaitingList> waitingLists;

    @OneToMany(mappedBy = "hospital", fetch = FetchType.LAZY,
    cascade = CascadeType.ALL,
    orphanRemoval = true)
    @JsonIgnore
    private List<AppointmentSlot> appointmentSlots;

    @OneToMany(mappedBy = "hospital",fetch = FetchType.LAZY,
    cascade = CascadeType.ALL,
    orphanRemoval = true)
    @JsonIgnore
    private List<Prescription> prescriptions;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hospitalId")
    private int hospitalId;

    @Column(name = "name")
    private String name;

    @Column(name = "city")
    private String city;

    @Column(name = "district")
    private String district;

    @Column(name = "streetAddress")
    private String streetAddress;

    @Column(name = "phoneNumber")
    private String phoneNumber;
}
