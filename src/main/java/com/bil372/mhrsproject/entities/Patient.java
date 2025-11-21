package com.bil372.mhrsproject.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Table(name = "patients")
@Entity
public class Patient {
    @Id
    @Column(name = "patientNationalId")
    private long patientNationalId;

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
}
