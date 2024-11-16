package com.obify.rms.controller;

import com.obify.rms.dto.RequestReviewCountDTO;
import com.obify.rms.dto.RequestReviewDTO;
import com.obify.rms.dto.ReviewDTO;
import com.obify.rms.dto.ReviewResponseDTO;
import com.obify.rms.entity.OrganizationEntity;
import com.obify.rms.repository.OrganizationRepository;
import com.obify.rms.service.ReviewService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/rms/api/v1/reviews")
@Validated
public class ReviewController {

    @Autowired
    private ReviewService reviewService;
    @Autowired
    private OrganizationRepository organizationRepository;

    @PostMapping("/count")
    public ResponseEntity<List<ReviewResponseDTO>> getReviewCounts(@RequestHeader("X-API-KEY") String apiKey, @RequestBody RequestReviewCountDTO reviewCountDTO){
        List<ReviewResponseDTO> counts = reviewService.getReviewCounts(apiKey, reviewCountDTO.getProductId());
        return new ResponseEntity<>(counts, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<ReviewDTO> addReview(@Valid @RequestBody ReviewDTO review){
        review = reviewService.addReview(review);
        return new ResponseEntity<>(review, HttpStatus.CREATED);
    }
    @PostMapping("/retrieve")
    public ResponseEntity<List<ReviewDTO>> getReviews(@RequestBody RequestReviewDTO requestReviewDTO, @RequestHeader("X-API-KEY") String apiKey){
        OrganizationEntity oe = organizationRepository.findByApiKeyAndActiveTrue(apiKey).get();
        Pageable pageable = PageRequest.of(requestReviewDTO.getPageNo(), requestReviewDTO.getPageSize());
        List<ReviewDTO> reviews = reviewService.getReviews(oe.getId(), requestReviewDTO.getProductId(), requestReviewDTO.getStatus(), pageable);
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }
    @PatchMapping("/updatestatus")
    public ResponseEntity<ReviewDTO> updateStatus(@Valid @RequestBody RequestReviewDTO requestReviewDTO){
        ReviewDTO reviewDTO = reviewService.updateStatus(requestReviewDTO);
        return new ResponseEntity<>(reviewDTO, HttpStatus.OK);
    }
}
