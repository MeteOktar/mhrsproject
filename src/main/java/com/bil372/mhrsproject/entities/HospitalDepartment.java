package com.bil372.mhrsproject.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Table(name = "hospital_departments")
@Entity
public class HospitalDepartment {
    @Id
    @Column(name = "departmentId")
    private int departmentId;
}
