package com.bil372.mhrsproject.services.security;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bil372.mhrsproject.repositories.DoctorRepository;
import com.bil372.mhrsproject.repositories.PatientRepository;
import com.bil372.mhrsproject.repositories.AdminRepository;
import com.bil372.mhrsproject.entities.Doctor;
import com.bil372.mhrsproject.entities.Patient;
import com.bil372.mhrsproject.entities.Admin;

@Service
public class CustomUserDetailsService implements UserDetailsService{

    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;
    private final AdminRepository adminRepository;

    public CustomUserDetailsService(DoctorRepository doctorRepository, PatientRepository patientRepository, AdminRepository adminRepository){
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
        this.adminRepository = adminRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // username = nationalId veya admin username
        try {
            long nationalId = Long.parseLong(username);

            // doktor mu
            Optional<Doctor> doctorOpt = doctorRepository.findByDoctorNationalId(nationalId);
            if (doctorOpt.isPresent()) {
                Doctor d = doctorOpt.get();
                return new MyUserDetails(d.getDoctorNationalId(), 
                                        d.getPasswordHash(), 
                                        List.of(new SimpleGrantedAuthority("ROLE_DOCTOR"))
                );
            }

            // 2) Hasta dene
            Optional<Patient> patientOpt = patientRepository.findByPatientNationalId(nationalId);
            if (patientOpt.isPresent()) {
                Patient p = patientOpt.get();
                return new MyUserDetails(
                        p.getPatientNationalId(),
                        p.getPasswordHash(),
                        List.of(new SimpleGrantedAuthority("ROLE_PATIENT"))
                );
            }

        } catch (NumberFormatException e) {
            //admin username olarak admin girmis olabilir
        }

        // 3) admin mi
        Optional<Admin> adminOpt = adminRepository.findByUsername(username);
        if (adminOpt.isPresent()) {
            Admin a = adminOpt.get();
            return User.builder()
                    .username(a.getUsername())
                    .password(a.getPasswordHash())
                    .roles("ADMIN")
                    .build();
        } 

        throw new UsernameNotFoundException("Kullanici bulunamadi: " + username);
    }
}
