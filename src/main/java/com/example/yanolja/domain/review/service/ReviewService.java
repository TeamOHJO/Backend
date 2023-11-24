package com.example.yanolja.domain.review.service;

import com.example.yanolja.domain.review.dto.AccommodationReviewResponse;
import com.example.yanolja.domain.review.dto.RoomReviewResponse;
import java.util.List;

public interface ReviewService {

    //    RoomReviewResponse createReview(Long roomId, ReviewCreateRequest request);
    List<AccommodationReviewResponse> getReviewsByAccommodationId(Long accommodationId);

    //    RoomReviewResponse updateReview(Long reviewId, ReviewUpdateRequest request);
    //    void deleteReview(Long reviewId);
}
