package com.parking.payments.repository;

import com.parking.payments.model.ServiceCredentials;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ServiceCredentialsRepository extends JpaRepository<ServiceCredentials, Long> {
    Optional<ServiceCredentials> findByUsername(String username);
}
