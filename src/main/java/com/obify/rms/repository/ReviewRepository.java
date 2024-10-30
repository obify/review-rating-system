package com.obify.rms.repository;

import com.obify.rms.entity.ReviewEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ReviewRepository extends MongoRepository<ReviewEntity, String> {
    List<ReviewEntity> findAllByOrganizationIdAndStatusContains(String organizationId, String status);
}
