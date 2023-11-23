package com.example.yanolja.domain.review.service;

import com.example.yanolja.domain.review.dto.ReviewCreateRequest;
import com.example.yanolja.domain.review.dto.ReviewResponse;
import com.example.yanolja.domain.review.dto.ReviewUpdateRequest;

public interface ReviewService {
    ReviewResponse createReview(Long roomId, ReviewCreateRequest request);
    ReviewResponse getReview(Long reviewId);
    ReviewResponse updateReview(Long reviewId, ReviewUpdateRequest request);
    void deleteReview(Long reviewId);
}
