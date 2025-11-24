package com.bil372.mhrsproject.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Table(name = "prescription_drugs")
@Entity
public class PrescriptionDrugs {

    @Id
    @Column(name = "prescriptionDrugId")
    private int prescriptionDrugId;     

    @ManyToOne
    @JoinColumn(name = "prescriptionId")
    private Prescription prescription;

    @Column(name = "drugName")
    private String drugName;

    @Column(name = "dosage")
    private String dosage;

    @Column(name = "route")
    private String route;

    @Column(name = "frequency")
    private String frequency;

    @Column(name = "durationDays")
    private int durationDays;

    @Column(name = "instructions")
    private String instructions;
}
