package com.example.repository;

import java.util.Optional;  
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.domain.Administrator;


public interface SecurityAdministratorRepository extends JpaRepository<Administrator, Integer> {
    Optional<Administrator> findByMailAddress(String mailAddress);
}
