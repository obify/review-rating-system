package com.obify.rms.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.obify.rms.entity.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReviewRepository extends JpaRepository<ReviewEntity, Long> {
    Long countByOrganizationIdAndProductIdContainingAndRatingBetween(Long organizationId, String productId, Float value1, Float value2);
    Optional<ReviewEntity> findByOrganizationIdAndProductIdContainingAndUserIdContaining(Long organizationId, String productId, String userId);
    Page<ReviewEntity> findAllByOrganizationIdAndProductIdAndStatusContainsOrderByCreatedAtAsc(Long organizationId, String productId, String status, Pageable pageable);
}
