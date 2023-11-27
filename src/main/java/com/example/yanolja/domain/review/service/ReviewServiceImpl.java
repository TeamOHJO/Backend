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
        for (String i : reviewCreateRequest.images()) {
            ReviewImages reviewImages = ReviewImages.builder()
                .review(review)
                .image(i)
                .build();
            reviewImageRepository.save(reviewImages);
        }

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
                review.getRoom().getAccommodation().getCategory(),
                review.getReviewContent(),
                review.getStar(),
                review.getUpdatedAt()))
            .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deleteReview(Long reviewId) {
        Review review = reviewRepository.findById(reviewId)
            .orElseThrow(() -> new ReviewNotFoundException(ErrorCode.REVIEW_NOT_FOUND));

        reviewRepository.delete(review);
    }
}
