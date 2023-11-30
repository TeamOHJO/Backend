package com.example.yanolja.domain.review.dto;

import com.example.yanolja.domain.accommodation.entity.Accommodation;
import com.example.yanolja.domain.accommodation.entity.AccommodationRooms;
import com.example.yanolja.domain.reservation.entity.Reservations;
import com.example.yanolja.domain.review.entity.Review;
import com.example.yanolja.domain.review.entity.ReviewImages;
import com.example.yanolja.domain.user.entity.User;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.List;


public record ReviewCreateRequest(
    @NotNull
    @Size(max = 500, message = "리뷰 내용은 500자를 넘으면 안됩니다.")
    String reviewContent,
    @NotNull
    List<String> images,
    @NotNull
    int star

) {
    public Review toEntity(AccommodationRooms accommodationRooms, User user,
        Reservations reservations, Accommodation accommodation) {
        return Review.builder()
            .accommodationRooms(accommodationRooms)
            .user(user)
            .reservations(reservations)
            .reviewContent(reviewContent)
            .star(star)
            .accommodation(accommodation)
            .build();
    }
}


