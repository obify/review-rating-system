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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
            dto.setRating("0-1");
            dto.setCount(count);
            reviewCounts.add(dto);

            count = reviewRepository.countByOrganizationIdAndProductIdContainingAndRatingBetween( optRe.get().getId(), productId,1.0f, 2.0f);
            dto = new ReviewResponseDTO();
            dto.setRating("1-2");
            dto.setCount(count);
            reviewCounts.add(dto);

            count = reviewRepository.countByOrganizationIdAndProductIdContainingAndRatingBetween( optRe.get().getId(),productId,2.0f, 3.0f);
            dto = new ReviewResponseDTO();
            dto.setRating("2-3");
            dto.setCount(count);
            reviewCounts.add(dto);

            count = reviewRepository.countByOrganizationIdAndProductIdContainingAndRatingBetween( optRe.get().getId(),productId,3.0f, 4.0f);
            dto = new ReviewResponseDTO();
            dto.setRating("3-4");
            dto.setCount(count);
            reviewCounts.add(dto);

            count = reviewRepository.countByOrganizationIdAndProductIdContainingAndRatingBetween( optRe.get().getId(),productId,4.0f, 5.0f);
            dto = new ReviewResponseDTO();
            dto.setRating("4-5");
            dto.setCount(count);
            reviewCounts.add(dto);
        }
        return reviewCounts;
    }

    @Override
    public ReviewDTO addReview(ReviewDTO dto) {
        Optional<ReviewEntity> optRe = reviewRepository.findByOrganizationIdAndUserIdContaining(dto.getOrganizationId(), dto.getUserId());
                //.orElseThrow(()->new BusinessException(List.of(new ErrorDTO("DUP_REVIEW_001", "User has already provided review"))));
        if(optRe.isPresent()){
            throw new BusinessException(List.of(new ErrorDTO("DUP_REVIEW_001", "User has already provided review")));
        }else {
            ReviewEntity re = new ReviewEntity();
            BeanUtils.copyProperties(dto, re);
            re.setCreatedAt(LocalDate.now());
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
        return dtoList;
    }
}
