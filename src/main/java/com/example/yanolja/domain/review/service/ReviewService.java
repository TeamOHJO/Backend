package com.example.yanolja.domain.review.service;

import com.example.yanolja.domain.review.dto.AccommodationReviewResponse;
import com.example.yanolja.domain.review.dto.ReviewCreateRequest;
import com.example.yanolja.domain.review.dto.ReviewUpdateRequest;
import com.example.yanolja.domain.review.dto.RoomReviewResponse;
import java.util.List;

public interface ReviewService {

    RoomReviewResponse createReview(Long roomId, ReviewCreateRequest request);

    List<AccommodationReviewResponse> getReviewsByAccommodationId(Long accommodationId);

    List<RoomReviewResponse> getReviewsByRoomId(Long roomId);

    RoomReviewResponse updateReview(Long reviewId, ReviewUpdateRequest request);
    void deleteReview(Long reviewId);
}
