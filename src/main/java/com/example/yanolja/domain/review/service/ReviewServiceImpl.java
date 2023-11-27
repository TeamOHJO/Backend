package com.example.yanolja.domain.review.service;

import com.example.yanolja.domain.accommodation.repository.AccommodationRoomRepository;
import com.example.yanolja.domain.reservation.entity.Reservations;
import com.example.yanolja.domain.reservation.exception.InvalidReservationException;
import com.example.yanolja.domain.reservation.repository.ReservationRepository;
import com.example.yanolja.domain.review.dto.AccommodationReviewResponse;
import com.example.yanolja.domain.review.dto.CreateReviewResponse;
import com.example.yanolja.domain.review.dto.ReviewCreateRequest;
import com.example.yanolja.domain.review.entity.Review;
import com.example.yanolja.domain.review.entity.ReviewImages;
import com.example.yanolja.domain.review.error.PermissionDeniedException;
import com.example.yanolja.domain.review.error.ReviewNotFoundException;
import com.example.yanolja.domain.review.repository.ReviewImageRepository;
import com.example.yanolja.domain.review.repository.ReviewRepository;
import com.example.yanolja.domain.user.entity.User;
import com.example.yanolja.global.exception.ErrorCode;
import com.example.yanolja.global.util.ResponseDTO;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final AccommodationRoomRepository roomRepository;
    private final ReservationRepository reservationRepository;
    private final ReviewImageRepository reviewImageRepository;

    @Override
    @Transactional
    public ResponseDTO<?> createReview(User user, Long reservationId,
        ReviewCreateRequest reviewCreateRequest) {
        Reservations reservations = reservationRepository.findById(reservationId)
            .orElseThrow(() -> new InvalidReservationException(ErrorCode.INVALID_RESERVATION_ID));

        Review review =
            reviewCreateRequest.toEntity(reservations.getRoom(),
                user, reservations, reservations.getRoom().getAccommodation());

        Review savedReview = reviewRepository.save(review);

        //image 리스트를 저장
        reviewCreateRequest.images().stream()
            .map(images -> ReviewImages.builder()
                .review(savedReview)
                .image(images)
                .build())
            .forEach(reviewImageRepository::save);

        return ResponseDTO.res(HttpStatus.CREATED, "리뷰 작성 성공",
            CreateReviewResponse.fromEntity(savedReview, reviewCreateRequest.images()));
    }

    @Override
    public List<AccommodationReviewResponse> getReviewsByAccommodationId(Long accommodationId) {
        return reviewRepository.findByAccommodationId(accommodationId).stream()
            .map(review -> new AccommodationReviewResponse(
                review.getReviewId(),
                review.getRoom().getRoomId(),
                review.getUser().getUsername(),
                review.getRoom().getAccommodation().getAccommodationName(),
                review.getRoom().getName(),
                review.getRoom().getAccommodation().getCategory(),
                review.getReviewContent(),
                review.getStar(),
                review.getReviewImages().stream().map(ReviewImages::getImage).collect(
                    Collectors.toList()),
                review.getUpdatedAt()))
            .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deleteReview(User user, Long reviewId) {
        Review review = reviewRepository.findById(reviewId)
            .orElseThrow(() -> new ReviewNotFoundException(ErrorCode.REVIEW_NOT_FOUND));

        //권한이 없는 경우
        reviewRepository.findByReviewIdAndUserId(reviewId, user.getUserId())
            .orElseThrow(() -> new PermissionDeniedException(ErrorCode.PERMISSION_DENIED));

        reviewImageRepository.deleteAll(
            reviewImageRepository.findAllByReviewReviewId(review.getReviewId()));
        reviewRepository.delete(review);
    }

    @Override
    public ResponseDTO<?> editReview(User user, Long reviewId, ReviewCreateRequest request) {

        Review review = reviewRepository.findById(reviewId)
            .orElseThrow(() -> new ReviewNotFoundException(ErrorCode.REVIEW_NOT_FOUND));
        //권한이 없는 경우
        reviewRepository.findByReviewIdAndUserId(reviewId, user.getUserId())
            .orElseThrow(() -> new PermissionDeniedException(ErrorCode.PERMISSION_DENIED));

        review.editReview(request.reviewContent(), request.star());
        Review savedReview = reviewRepository.save(review);

        reviewImageRepository.deleteAll(
            reviewImageRepository.findAllByReviewReviewId(review.getReviewId()));

        request.images().stream()
            .map(images -> ReviewImages.builder()
                .review(savedReview)
                .image(images)
                .build())
            .forEach(reviewImageRepository::save);

        return ResponseDTO.res(HttpStatus.OK, "리뷰 수정 성공",
            CreateReviewResponse.fromEntity(savedReview, request.images()));
    }
}
