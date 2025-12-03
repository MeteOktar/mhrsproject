package com.bil372.mhrsproject.DTOs;

import lombok.Data;

@Data
public class CreateHospitalRequest {
    private String name;
    private String city;
    private String district;
    private String streetAddress;
    private String phoneNumber;
}
