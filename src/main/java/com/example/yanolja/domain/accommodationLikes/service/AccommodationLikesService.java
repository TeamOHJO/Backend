package com.example.yanolja.domain.accommodationLikes.service;

import com.example.yanolja.domain.accommodation.entity.Accommodation;
import com.example.yanolja.domain.accommodationLikes.dto.AccommodationLikesResponse;
import com.example.yanolja.domain.user.entity.User;
import com.example.yanolja.global.util.ResponseDTO;

public interface AccommodationLikesService {

    ResponseDTO<AccommodationLikesResponse> toggleAccommodationLike(User user,
        Long accommodationId);

}
