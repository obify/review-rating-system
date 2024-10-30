package com.obify.rms.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.obify.rms.entity.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<ReviewEntity, Long> {
    Page<ReviewEntity> findAllByOrganizationIdAndStatusContainsOrderByCreatedAtAsc(Long organizationId, String status, Pageable pageable);
}
