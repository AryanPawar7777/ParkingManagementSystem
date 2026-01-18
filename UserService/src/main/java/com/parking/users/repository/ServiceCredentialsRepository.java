package com.parking.users.repository;

import com.parking.users.model.ServiceCredentials;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ServiceCredentialsRepository extends JpaRepository<ServiceCredentials, Long> {
    Optional<ServiceCredentials> findByUsername(String username);
}
