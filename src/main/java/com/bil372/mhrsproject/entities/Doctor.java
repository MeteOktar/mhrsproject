package com.bil372.mhrsproject.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Table(name= "doctors")
@Entity
public class Doctor {
    @Id
    @Column(name = "doctorNationalId")
    private long doctorNationalId;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "hospitalId")
    private int hospitalId;

    @Column(name = "departmentId")
    private int departmentId;

}
