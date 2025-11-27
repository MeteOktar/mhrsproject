package com.bil372.mhrsproject.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.bil372.mhrsproject.entities.Admin;

public interface AdminRepository extends JpaRepository<Admin, String> {

    Optional<Admin> findByUsername(String username);
}
