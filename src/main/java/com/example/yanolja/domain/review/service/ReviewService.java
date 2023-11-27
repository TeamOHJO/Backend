package com.example.yanolja.domain.review.service;

import com.example.yanolja.domain.review.dto.AccommodationReviewResponse;
import com.example.yanolja.domain.review.dto.ReviewCreateRequest;
import com.example.yanolja.domain.review.dto.ReviewUpdateRequest;
import com.example.yanolja.domain.review.dto.RoomReviewResponse;
import com.example.yanolja.domain.user.entity.User;
import com.example.yanolja.global.util.ResponseDTO;
import java.util.List;

public interface ReviewService {

    ResponseDTO<?> createReview(User user,Long reservationId, ReviewCreateRequest request);

    List<AccommodationReviewResponse> getReviewsByAccommodationId(Long accommodationId);


    void deleteReview(Long reviewId);
}
