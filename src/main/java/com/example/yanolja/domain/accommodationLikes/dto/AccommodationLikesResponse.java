package com.example.yanolja.domain.accommodationLikes.dto;

import com.example.yanolja.domain.accommodation.entity.Accommodation;
import com.example.yanolja.domain.accommodationLikes.entity.AccommodationLikes;
import com.example.yanolja.domain.user.entity.User;
import lombok.Value;

@Value
public class AccommodationLikesResponse {

    Long accommodationId;
    boolean isLiked;

    public AccommodationLikes toEntity(
        User user, Accommodation accommodation, boolean isLike) {

        return AccommodationLikes.builder()
            .user(user)
            .accommodation(accommodation)
            .isLike(isLike)
            .build();
    }
}
