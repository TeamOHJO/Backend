package com.example.yanolja.global.util;

import com.example.yanolja.domain.review.entity.Review;
import com.example.yanolja.domain.review.repository.ReviewRepository;

public class ReviewRatingUtils {

    public static double calculateAverageRating(Long accommodationId,
        ReviewRepository reviewRepository) {
        return Math.round(reviewRepository.findByAccommodationId(accommodationId).stream()
            .mapToInt(Review::getStar)
            .average()
            .orElse(0.0) * 10) / 10.0;
    }
}
