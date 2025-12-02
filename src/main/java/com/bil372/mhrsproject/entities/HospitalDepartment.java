package com.bil372.mhrsproject.entities;

import java.util.List;

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
@Table(name = "hospital_departments")
@Entity
public class HospitalDepartment {

    @ManyToOne
    @JoinColumn(name = "hospitalId")
    @JsonIgnore
    private Hospital hospital;

    @OneToMany(mappedBy = "department", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Prescription> departmentPrescriptions;

    @OneToMany(mappedBy = "department", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Doctor> departmentDoctors;

    @OneToMany(mappedBy = "department", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<AppointmentSlot>  dAppointmentSlots;

    @OneToMany(mappedBy = "department", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<WaitingList> dWaitingLists;

    @Id
    @Column(name = "departmentId")
    private int departmentId;

    @Column(name = "branchName")
    private String branchName;
}
