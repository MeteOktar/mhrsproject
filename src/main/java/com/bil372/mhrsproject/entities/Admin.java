package com.bil372.mhrsproject.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import jakarta.persistence.Column;

@Data
@Entity
@Table(name = "admins")
public class Admin {

    @Id
    @Column(name = "username")
    private String username;   // admin usernamde

    @Column(name = "passwordHash")
    private String passwordHash;   // simdilik hashlenmedi

    public Admin() {}

    public Admin(String username, String password) {
        this.username = username;
        this.passwordHash = password;
    }
}
