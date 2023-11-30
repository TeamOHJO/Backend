package com.example.yanolja.domain.review.controller;

import com.example.yanolja.domain.review.dto.AccommodationReviewResponse;
import com.example.yanolja.domain.review.dto.ReviewCreateRequest;
import com.example.yanolja.domain.review.dto.UserReviewDTO;
import com.example.yanolja.domain.review.service.ReviewService;
import com.example.yanolja.global.springsecurity.PrincipalDetails;
import com.example.yanolja.global.util.ResponseDTO;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/review")
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/reservations/{reservationId}")
    public ResponseEntity<ResponseDTO<?>> createReview(
        @AuthenticationPrincipal PrincipalDetails principalDetails,
        @PathVariable Long reservationId,
        @Valid @RequestBody ReviewCreateRequest request) {

        ResponseDTO<?> response = reviewService.createReview(
            principalDetails.getUser(), reservationId, request);
        return ResponseEntity.status(response.getCode()).body(response);
    }

    @GetMapping("/accommodation/{accommodationId}")
    public ResponseEntity<ResponseDTO<List<AccommodationReviewResponse>>> getReviewsByAccommodationId(
        @PathVariable Long accommodationId) {
        List<AccommodationReviewResponse> reviews = reviewService.getReviewsByAccommodationId(
            accommodationId);
        return ResponseEntity.ok(ResponseDTO.res(HttpStatus.OK, "조회 성공", reviews));
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<ResponseDTO<Object>> deleteReview(
        @AuthenticationPrincipal PrincipalDetails principalDetails,
        @PathVariable Long reviewId) {

        reviewService.deleteReview(principalDetails.getUser(), reviewId);
        return ResponseEntity.ok(ResponseDTO.res(HttpStatus.OK, "리뷰가 성공적으로 삭제되었습니다."));
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<ResponseDTO<?>> editReview(
        @AuthenticationPrincipal PrincipalDetails principalDetails,
        @PathVariable Long reviewId,
        @Valid @RequestBody ReviewCreateRequest request) {

        ResponseDTO<?> response = reviewService.editReview(
            principalDetails.getUser(), reviewId, request);
        return ResponseEntity.status(response.getCode()).body(response);
    }

    @GetMapping("/user/list")
    public ResponseEntity<ResponseDTO<List<UserReviewDTO>>> getUserReviews(
        @AuthenticationPrincipal PrincipalDetails principalDetails) {

        ResponseDTO<List<UserReviewDTO>> response = reviewService.getUserReviews(principalDetails.getUser());
        return ResponseEntity.ok(response);
    }
}
