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
import jakarta.persistence.UniqueConstraint;
import lombok.Data;

@Data
@Table(name = "appointment_slots",
    uniqueConstraints = {
        @UniqueConstraint(
            name = "uq_doctor_patient_slot",
            columnNames = {"doctorNationalId", "patientNationalId", "slotDateTime"}
        )
    }
)
@Entity
public class AppointmentSlot {
    @Id
    @Column(name="appointmentId")
    private int appointmentId;

    @ManyToOne
    @JoinColumn(name = "doctorNationalId")
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "hospitalId")
    private Hospital hospital;

    @ManyToOne
    @JoinColumn(name = "departmentId")
    private HospitalDepartment department;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime slotDateTime;

    @ManyToOne
    @JoinColumn(name = "patientNationalId")
    private Patient patient;

    @OneToMany(mappedBy = "appointmentSlot", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Prescription> appointmentPrescriptions;

    @Column(name = "status")
    private String status;

}
