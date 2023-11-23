package com.example.yanolja.domain.review.service;

import com.example.yanolja.domain.accommodation.repository.AccommodationRoomRepository;
import com.example.yanolja.domain.review.dto.ReviewCreateRequest;
import com.example.yanolja.domain.review.dto.ReviewResponse;
import com.example.yanolja.domain.review.dto.ReviewUpdateRequest;
import com.example.yanolja.domain.review.repository.ReviewRepository;
import com.example.yanolja.domain.user.repository.UserRepository;
import org.springframework.transaction.annotation.Transactional;

public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final AccommodationRoomRepository roomRepository;

    @Override
    @Transactional
    public ReviewResponse createReview(Long roomId, ReviewCreateRequest request) {

    }

    @Override
    @Transactional(readOnly = true)
    public ReviewResponse getReview(Long reviewId) {

    }

    @Override
    @Transactional
    public ReviewResponse updateReview(Long reviewId, ReviewUpdateRequest request) {

    }

    @Override
    @Transactional
    public void deleteReview(Long reviewId) {

    }

}
