package com.obify.rms.service.impl;

import com.obify.rms.dto.ErrorDTO;
import com.obify.rms.dto.ReviewDTO;
import com.obify.rms.entity.ReviewEntity;
import com.obify.rms.exception.BusinessException;
import com.obify.rms.repository.ReviewRepository;
import com.obify.rms.service.ReviewService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@NoArgsConstructor
@Service
public class ReviewServiceImpl implements ReviewService {

    private ReviewRepository reviewRepository;

    @Override
    public ReviewDTO addReview(ReviewDTO dto) {
        ReviewEntity re = new ReviewEntity();
        BeanUtils.copyProperties(dto, re);
        re.setCreatedAt(LocalDate.now());
        re = reviewRepository.save(re);
        BeanUtils.copyProperties(re, dto);
        return dto;
    }

    @Override
    public ReviewDTO updateStatus(ReviewDTO dto) {
        ReviewEntity re = reviewRepository.findById(dto.getId())
                .orElseThrow(()->new BusinessException(List.of(new ErrorDTO("NOT_FOUND", "Review id not found"))));
        re.setStatus(dto.getStatus());
        re = reviewRepository.save(re);
        BeanUtils.copyProperties(re, dto);
        return dto;
    }

    @Override
    public List<ReviewDTO> getReviews(Long organizationId, String status, Pageable pageable) {
        Page<ReviewEntity> pgReviews = reviewRepository.findAllByOrganizationIdAndStatusContainsOrderByCreatedAtAsc(organizationId, status, pageable);
        List<ReviewDTO> dtoList = null;
        if(!pgReviews.isEmpty()){
            dtoList = pgReviews.stream().map(re->{
                ReviewDTO reviewDTO = new ReviewDTO();
                BeanUtils.copyProperties(re, reviewDTO);
                return reviewDTO;
            }).collect(Collectors.toList());
        }
        return dtoList;
    }
}
