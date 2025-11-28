package com.bil372.mhrsproject.services.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class MyUserDetails extends User {
    private final long nationalId;

    public MyUserDetails(long nationalId, String password, Collection<GrantedAuthority> roles){
        super(password, password, roles);
        this.nationalId = nationalId;
    }

    public long getNationalId(){
        return nationalId;
    }
}
