// src/main/java/com/bil372/mhrsproject/dto/PatientRegisterRequest.java
package com.bil372.mhrsproject.DTOs;

import lombok.Data;

@Data
public class PatientRegisterRequest {
    private long nationalId;
    private String firstName;
    private String lastName;
    private int heightCm;
    private int weightKg;
    private String bloodGroup;
    private String password;
}
