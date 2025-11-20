package com.bil372.mhrsproject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())                 // CSRF kapalı
            .authorizeHttpRequests(auth -> auth
                .anyRequest().permitAll()                 // HER ŞEY serbest
            )
            .formLogin(login -> login.disable())          // Login formunu kapat
            .httpBasic(basic -> basic.disable());         // Basic Auth da kapalı

        return http.build();
    }
}
