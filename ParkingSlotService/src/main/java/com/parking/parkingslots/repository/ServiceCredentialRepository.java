package com.parking.parkingslots.repository;

import com.parking.parkingslots.model.ServiceCredential;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ServiceCredentialRepository extends JpaRepository<ServiceCredential, Long> {
    Optional<ServiceCredential> findByUsername(String username);
}
