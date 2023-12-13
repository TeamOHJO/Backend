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

    public UserReviewResponse(Review review) {
        this.reviewId = review.getReviewId();
        this.star = review.getStar();
        this.reviewImages = review.getReviewImages().stream()
            .map(ReviewImages::getImage)
            .collect(Collectors.toList());
        this.accommodationCategory = review.getRoom().getAccommodation().getCategory()
            .getCategory();
        this.accommodationName = review.getRoom().getAccommodation().getAccommodationName();
        this.roomName = review.getRoom().getName();

        if (review.getReservation() != null) {
            this.startDate = review.getReservation().getStartDate();
            this.endDate = review.getReservation().getEndDate();
        } else {
            this.startDate = null;
            this.endDate = null;
        }

        this.reviewContent = review.getReviewContent();
    }
}
