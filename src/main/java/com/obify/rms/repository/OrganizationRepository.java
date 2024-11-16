package com.obify.rms.repository;

import com.obify.rms.entity.OrganizationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrganizationRepository extends JpaRepository<OrganizationEntity, Long> {
    Optional<OrganizationEntity> findByApiKeyAndActiveTrue(String apiKey);
    Optional<OrganizationEntity> findByEmail(String email);
}
