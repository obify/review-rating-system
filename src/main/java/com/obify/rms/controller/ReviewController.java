package com.obify.rms.controller;

import com.obify.rms.dto.ReviewDTO;
import com.obify.rms.entity.OrganizationEntity;
import com.obify.rms.entity.ReviewEntity;
import com.obify.rms.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewController {

    @Autowired
    private ReviewRepository reviewRepository;

    @PostMapping
    public ResponseEntity<ReviewEntity> addReview(@RequestBody ReviewEntity review){
        review = reviewRepository.save(review);
        return new ResponseEntity<>(review, HttpStatus.CREATED);
    }
    @GetMapping("/{organizationId}")
    public ResponseEntity<List<ReviewEntity>> getReviews(@PathVariable String organizationId){
        List<ReviewEntity> reviews = reviewRepository.findAllByOrganizationIdAndStatusContains(organizationId, "publish");
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
