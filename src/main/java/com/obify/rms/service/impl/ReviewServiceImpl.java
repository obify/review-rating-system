package com.obify.rms.service.impl;

import com.obify.rms.dto.ErrorDTO;
import com.obify.rms.dto.RequestReviewDTO;
import com.obify.rms.dto.ReviewDTO;
import com.obify.rms.dto.ReviewResponseDTO;
import com.obify.rms.entity.OrganizationEntity;
import com.obify.rms.entity.ReviewEntity;
import com.obify.rms.exception.BusinessException;
import com.obify.rms.repository.ReviewRepository;
import com.obify.rms.service.ReviewService;
import com.obify.rms.util.CommonUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private CommonUtil commonUtil;

    @Override
    public List<ReviewResponseDTO> getReviewCounts(String apiKey, String productId) {
        Optional<OrganizationEntity> optRe = commonUtil.getOrganization(apiKey);
        List<ReviewResponseDTO> reviewCounts = null;
        if(optRe.isPresent()){
            reviewCounts = new ArrayList<>();
            ReviewResponseDTO dto = null;
            Long count = reviewRepository.countByOrganizationIdAndProductIdContainingAndRatingBetween( optRe.get().getId(), productId,0.0f, 1.0f);
            dto = new ReviewResponseDTO();
            dto.setRating(1.0f);
            dto.setCount(count);
            dto.setComment("Very Poor");
            reviewCounts.add(dto);

            count = reviewRepository.countByOrganizationIdAndProductIdContainingAndRatingBetween( optRe.get().getId(), productId,1.1f, 2.0f);
            dto = new ReviewResponseDTO();
            dto.setRating(2.0f);
            dto.setCount(count);
            dto.setComment("Poor");
            reviewCounts.add(dto);

            count = reviewRepository.countByOrganizationIdAndProductIdContainingAndRatingBetween( optRe.get().getId(),productId,2.1f, 3.0f);
            dto = new ReviewResponseDTO();
            dto.setRating(3.0f);
            dto.setCount(count);
            dto.setComment("Average");
            reviewCounts.add(dto);

            count = reviewRepository.countByOrganizationIdAndProductIdContainingAndRatingBetween( optRe.get().getId(),productId,3.1f, 4.0f);
            dto = new ReviewResponseDTO();
            dto.setRating(4.0f);
            dto.setCount(count);
            dto.setComment("Good");
            reviewCounts.add(dto);

            count = reviewRepository.countByOrganizationIdAndProductIdContainingAndRatingBetween( optRe.get().getId(),productId,4.1f, 5.0f);
            dto = new ReviewResponseDTO();
            dto.setRating(5.0f);
            dto.setCount(count);
            dto.setComment("Excellent");
            reviewCounts.add(dto);
        }
        Collections.sort(reviewCounts, new Comparator<ReviewResponseDTO>() {
            @Override
            public int compare(ReviewResponseDTO u1, ReviewResponseDTO u2) {
                return u2.getRating().compareTo(u1.getRating());
            }
        });
        return reviewCounts;
    }

    @Override
    public ReviewDTO addReview(ReviewDTO dto) {
        Optional<ReviewEntity> optRe = reviewRepository.findByOrganizationIdAndProductIdContainingAndUserIdContaining(dto.getOrganizationId(), dto.getProductId(), dto.getUserId());
                //.orElseThrow(()->new BusinessException(List.of(new ErrorDTO("DUP_REVIEW_001", "User has already provided review"))));
        if(optRe.isPresent()){
            throw new BusinessException(List.of(new ErrorDTO("DUP_REVIEW_001", "User has already provided review")));
        }else {
            ReviewEntity re = new ReviewEntity();
            BeanUtils.copyProperties(dto, re);
            re.setCreatedAt(LocalDateTime.now());
            re = reviewRepository.save(re);
            BeanUtils.copyProperties(re, dto);
        }
        return dto;
    }

    @Override
    public ReviewDTO updateStatus(RequestReviewDTO dto) {
        ReviewEntity re = reviewRepository.findById(dto.getReviewId())
                .orElseThrow(()->new BusinessException(List.of(new ErrorDTO("NOT_FOUND", "Review id not found"))));
        re.setStatus(dto.getStatus());
        re = reviewRepository.save(re);
        ReviewDTO reviewDTO = new ReviewDTO();
        BeanUtils.copyProperties(re, reviewDTO);
        return reviewDTO;
    }

    @Override
    public List<ReviewDTO> getReviews(Long organizationId, String productId, String status, Pageable pageable) {
        Page<ReviewEntity> pgReviews = reviewRepository.findAllByOrganizationIdAndProductIdAndStatusContainsOrderByCreatedAtAsc(organizationId, productId, status, pageable);
        List<ReviewDTO> dtoList = null;
        if(!pgReviews.isEmpty()){
            dtoList = pgReviews.stream().map(re->{
                ReviewDTO reviewDTO = new ReviewDTO();
                BeanUtils.copyProperties(re, reviewDTO);
                return reviewDTO;
            }).collect(Collectors.toList());
        }else{
            throw new BusinessException(List.of(new ErrorDTO("NOT_FOUND", "No reviews found for the product")));
        }
        Collections.sort(dtoList, new Comparator<ReviewDTO>() {
            @Override
            public int compare(ReviewDTO u1, ReviewDTO u2) {
                return u2.getCreatedAt().compareTo(u1.getCreatedAt());
            }
        });
        return dtoList;
    }
}
