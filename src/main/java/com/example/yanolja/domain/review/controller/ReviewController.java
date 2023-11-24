package com.example.yanolja.domain.review.controller;

import com.example.yanolja.domain.review.dto.AccommodationReviewResponse;
import com.example.yanolja.domain.review.dto.RoomReviewResponse;
import com.example.yanolja.domain.review.service.ReviewService;
import com.example.yanolja.global.util.ResponseDTO;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/review")
public class ReviewController {

    private final ReviewService reviewService;

//    @PostMapping("/{roomId}")
//    public ResponseEntity<ResponseDTO<RoomReviewResponse>> createReview(@PathVariable Long roomId,
//        @Valid @RequestBody ReviewCreateRequest request) {
//        RoomReviewResponse reviewResponse = reviewService.createReview(roomId, request);
//        return ResponseEntity.status(HttpStatus.CREATED)
//            .body(ResponseDTO.res(HttpStatus.CREATED, "리뷰가 성공적으로 생성되었습니다.", reviewResponse));
//    }

//    @GetMapping("/room/{roomId}")
//    public ResponseEntity<List<RoomReviewResponse>> getReviewsByRoomId(@PathVariable Long roomId) {
//        List<RoomReviewResponse> reviews = reviewService.getReviewsByRoomId(roomId);
//        return ResponseEntity.ok(reviews);
//    }

    @GetMapping("/accommodation/{accommodationId}")
    public ResponseEntity<ResponseDTO<List<AccommodationReviewResponse>>> getReviewsByAccommodationId(
        @PathVariable Long accommodationId) {
        List<AccommodationReviewResponse> reviews = reviewService.getReviewsByAccommodationId(
            accommodationId);
        return ResponseEntity.ok(ResponseDTO.res(HttpStatus.OK, reviews));
    }

//    @PutMapping("/{reviewId}")
//    public ResponseEntity<ResponseDTO<RoomReviewResponse>> updateReview(@PathVariable Long reviewId,
//        @Valid @RequestBody
//        ReviewUpdateRequest request) {
//        RoomReviewResponse reviewResponse = reviewService.updateReview(reviewId, request);
//        return ResponseEntity.ok(ResponseDTO.res(HttpStatus.OK, "리뷰 업데이트 성공", reviewResponse));
//    }
//
//    @DeleteMapping("/{reviewId}")
//    public ResponseEntity<HttpStatus> deleteReview(@PathVariable Long reviewId) {
//        reviewService.deleteReview(reviewId);
//        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
//    }

}
