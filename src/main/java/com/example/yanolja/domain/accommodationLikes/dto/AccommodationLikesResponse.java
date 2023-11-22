package com.example.yanolja.domain.accommodationLikes.dto;

import lombok.Value;

@Value
public class AccommodationLikesResponse {

    Long accommodationId;
    boolean isLiked;

}
