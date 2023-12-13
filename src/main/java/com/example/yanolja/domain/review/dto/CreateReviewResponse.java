package com.example.yanolja.domain.review.dto;

import com.example.yanolja.domain.review.entity.Review;
import java.util.List;


public record CreateReviewResponse(

    String reviewContent,

    List<String> images,

    int star

) {

    public static CreateReviewResponse fromReview(Review review,
        List<String> images) {
        return new CreateReviewResponse(
            review.getReviewContent(),
            images,
            review.getStar()

        );


    }

}
