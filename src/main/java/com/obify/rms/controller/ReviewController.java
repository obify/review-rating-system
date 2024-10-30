package com.obify.rms.controller;

import com.obify.rms.dto.RequestReviewDTO;
import com.obify.rms.dto.ReviewDTO;
import com.obify.rms.entity.ReviewEntity;
import com.obify.rms.repository.ReviewRepository;
import com.obify.rms.service.ReviewService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@NoArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/rms/api/v1/reviews")
public class ReviewController {

    private ReviewService reviewService;

    @PostMapping
    public ResponseEntity<ReviewDTO> addReview(@RequestBody ReviewDTO review){
        review = reviewService.addReview(review);
        return new ResponseEntity<>(review, HttpStatus.CREATED);
    }
    @PostMapping("/all")
    public ResponseEntity<List<ReviewEntity>> getReviews(@RequestBody RequestReviewDTO requestReviewDTO){
        Pageable pageable = PageRequest.of(requestReviewDTO.getPageNo(), requestReviewDTO.getPageSize());
        List<ReviewEntity> reviews = reviewService.getReviews(requestReviewDTO.getOrganizationId(), requestReviewDTO.getStatus(), pageable);
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }
    @PatchMapping("/{reviewId}")
    public ResponseEntity<ReviewEntity> updateStatus(@PathVariable String reviewId, @RequestBody ReviewDTO reviewDTO){
        ReviewEntity re = reviewRepository.findById(reviewId).get();
        re.setStatus(reviewDTO.getStatus());
        re = reviewRepository.save(re);
        return new ResponseEntity<>(re, HttpStatus.OK);
    }
}
