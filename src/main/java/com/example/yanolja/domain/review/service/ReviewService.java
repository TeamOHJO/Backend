package com.example.yanolja.domain.review.service;

import com.example.yanolja.domain.review.dto.AccommodationReviewResponse;
import com.example.yanolja.domain.review.dto.CreateReviewRequest;
import com.example.yanolja.domain.review.dto.UpdateReviewRequest;
import com.example.yanolja.domain.review.dto.UserReviewResponse;
import com.example.yanolja.domain.user.entity.User;
import com.example.yanolja.global.util.ResponseDTO;
import java.util.List;

public interface ReviewService {

    ResponseDTO<?> createReview(User user, Long reservationId, CreateReviewRequest request);

    List<AccommodationReviewResponse> getReviewsByAccommodationId(Long accommodationId);

    void deleteReview(User user, Long reviewId);

    ResponseDTO<?> editReview(User user, Long reviewId, UpdateReviewRequest request);

    ResponseDTO<List<UserReviewResponse>> getUserReviews(User user);
}
