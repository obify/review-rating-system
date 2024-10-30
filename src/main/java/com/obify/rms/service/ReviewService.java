package com.obify.rms.service;

import com.obify.rms.dto.RequestReviewDTO;
import com.obify.rms.dto.ReviewDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ReviewService {
    ReviewDTO addReview(ReviewDTO dto);
    ReviewDTO updateStatus(RequestReviewDTO dto);
    List<ReviewDTO> getReviews(Long organizationId, String status, Pageable pageable);
}
