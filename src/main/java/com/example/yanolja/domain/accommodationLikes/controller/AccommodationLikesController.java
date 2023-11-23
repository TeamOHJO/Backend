package com.example.yanolja.domain.accommodationLikes.controller;

import com.example.yanolja.domain.accommodation.entity.Accommodation;
import com.example.yanolja.domain.accommodationLikes.dto.AccommodationLikesResponse;
import com.example.yanolja.domain.accommodationLikes.service.AccommodationLikesService;
import com.example.yanolja.domain.user.entity.User;
import com.example.yanolja.global.springsecurity.PrincipalDetails;
import com.example.yanolja.global.util.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accommodation")
public class AccommodationLikesController {

    private final AccommodationLikesService accommodationLikesService;

    public AccommodationLikesController(AccommodationLikesService accommodationLikesService) {
        this.accommodationLikesService = accommodationLikesService;
    }

    @PostMapping("/{accommodationId}/likes")

    public ResponseEntity<ResponseDTO<AccommodationLikesResponse>> toggleLike(

        @PathVariable Long accommodationId,
        @AuthenticationPrincipal PrincipalDetails principalDetails) {

        User user = principalDetails.getUser();
        ResponseDTO<Boolean> isLiked = accommodationLikesService.toggleAccommodationLike(user,
            accommodationId);

        AccommodationLikesResponse response = new AccommodationLikesResponse(
            accommodationId,
            isLiked.getData());

        return ResponseEntity.ok(
            ResponseDTO.res(HttpStatus.OK, "좋아요 상태가 성공적으로 변경되었습니다.", response));

    }


}
