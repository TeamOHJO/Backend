package com.example.yanolja.domain.review.controller;

import com.example.yanolja.domain.review.dto.ReviewCreateRequest;
import com.example.yanolja.domain.review.dto.ReviewResponse;
import com.example.yanolja.domain.review.dto.ReviewUpdateRequest;
import com.example.yanolja.domain.review.service.ReviewService;
import com.example.yanolja.global.util.ResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/{roomId}")
    public ResponseEntity<ResponseDTO<ReviewResponse>> createReview(@PathVariable Long roomId,
        @Valid @RequestBody ReviewCreateRequest request) {
        ReviewResponse reviewResponse = reviewService.createReview(roomId, request);
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(ResponseDTO.res(HttpStatus.CREATED, "리뷰가 성공적으로 생성되었습니다.", reviewResponse));
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<ResponseDTO<ReviewResponse>> getReview(@PathVariable Long reviewId) {
        ReviewResponse reviewResponse = reviewService.getReview(reviewId);
        return ResponseEntity.ok(ResponseDTO.res(HttpStatus.OK, "리뷰 조회 성공", reviewResponse));
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<ResponseDTO<ReviewResponse>> updateReview(@PathVariable Long reviewId, @Valid @RequestBody
        ReviewUpdateRequest request) {
        ReviewResponse reviewResponse = reviewService.updateReview(reviewId, request);
        return ResponseEntity.ok(ResponseDTO.res(HttpStatus.OK, "리뷰 업데이트 성공", reviewResponse));
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<HttpStatus> deleteReview(@PathVariable Long reviewId) {
        reviewService.deleteReview(reviewId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}