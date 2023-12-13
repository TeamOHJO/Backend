package com.example.yanolja.domain.review.dto;

import com.example.yanolja.domain.review.entity.Review;
import com.example.yanolja.domain.review.entity.ReviewImages;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.List;
import java.util.stream.Collectors;

public record CreateReviewResponse(
    @NotNull
    @Size(max = 500, message = "리뷰 내용은 500자를 넘으면 안됩니다.")
    String reviewContent,
    @NotNull
    List<String> images,
    @NotNull
    int star

) {
    public static CreateReviewResponse fromReview(Review review,
        List<String> images) {
        return new CreateReviewResponse(
            review.getReviewContent(),
            review.getReviewImages().stream()
                .map(ReviewImages::getImage)
                .collect(Collectors.toList()),
            review.getStar()

        );


    }

}
