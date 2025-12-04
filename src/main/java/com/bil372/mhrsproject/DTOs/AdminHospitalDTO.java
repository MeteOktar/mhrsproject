package com.bil372.mhrsproject.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminHospitalDTO {
    private String id;
    private String name;
    private String city;
    private String district;
}
