package com.obify.rms.service;

import com.obify.rms.dto.RequestReviewDTO;
import com.obify.rms.dto.ReviewDTO;
import com.obify.rms.dto.ReviewResponseDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ReviewService {
    List<ReviewResponseDTO> getReviewCounts(String apiKey, String productId);
    ReviewDTO addReview(ReviewDTO dto);
    ReviewDTO updateStatus(RequestReviewDTO dto);
    List<ReviewDTO> getReviews(Long organizationId, String productId, String status, Pageable pageable);
}
