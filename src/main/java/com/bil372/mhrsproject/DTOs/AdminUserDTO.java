package com.bil372.mhrsproject.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminUserDTO {
    private String username;
    private String role;
    private String password;
}
