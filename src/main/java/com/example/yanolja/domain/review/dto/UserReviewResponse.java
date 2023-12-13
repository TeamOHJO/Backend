package com.example.yanolja.domain.review.dto;

import com.example.yanolja.domain.review.entity.Review;
import com.example.yanolja.domain.review.entity.ReviewImages;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserReviewResponse {

    private Long reviewId;
    private int star;
    private List<String> reviewImages;
    private String accommodationCategory;
    private String accommodationName;
    private String roomName;
    private LocalDate startDate;
    private LocalDate endDate;
    private String reviewContent;

    public static UserReviewResponse fromReview(Review review) {
        List<String> reviewImages = review.getReviewImages().stream()
            .map(ReviewImages::getImage)
            .collect(Collectors.toList());

        String accommodationCategory = review.getRoom().getAccommodation().getCategory()
            .getCategory();
        String accommodationName = review.getRoom().getAccommodation().getAccommodationName();
        String roomName = review.getRoom().getName();

        LocalDate startDate = null;
        LocalDate endDate = null;
        if (review.getReservation() != null) {
            startDate = review.getReservation().getStartDate();
            endDate = review.getReservation().getEndDate();
        }

        String reviewContent = review.getReviewContent();
        int star = review.getStar();

        return new UserReviewResponse(review.getReviewId(), star, reviewImages,
            accommodationCategory, accommodationName, roomName,
            startDate, endDate, reviewContent);
    }


    private UserReviewResponse(Long reviewId, int star, List<String> reviewImages,
        String accommodationCategory, String accommodationName, String roomName,
        LocalDate startDate, LocalDate endDate, String reviewContent) {
        this.reviewId = reviewId;
        this.star = star;
        this.reviewImages = reviewImages;
        this.accommodationCategory = accommodationCategory;
        this.accommodationName = accommodationName;
        this.roomName = roomName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.reviewContent = reviewContent;
    }
}

