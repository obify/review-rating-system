package com.obify.rms.repository;

import com.obify.rms.entity.OrganizationEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrganizationRepository extends MongoRepository<OrganizationEntity, String> {
}
