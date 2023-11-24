package com.example.yanolja.domain.review.service;

import com.example.yanolja.domain.accommodation.entity.AccommodationRooms;
import com.example.yanolja.domain.accommodation.repository.AccommodationRoomRepository;
import com.example.yanolja.domain.review.dto.ReviewCreateRequest;
import com.example.yanolja.domain.review.dto.ReviewResponse;
import com.example.yanolja.domain.review.dto.ReviewUpdateRequest;
import com.example.yanolja.domain.review.entity.Review;
import com.example.yanolja.domain.review.error.ReviewNotFoundException;
import com.example.yanolja.domain.review.repository.ReviewRepository;
import com.example.yanolja.domain.user.entity.User;
import com.example.yanolja.domain.user.repository.UserRepository;
import com.example.yanolja.global.exception.ErrorCode;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final AccommodationRoomRepository roomRepository;

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository,
        UserRepository userRepository,
        AccommodationRoomRepository roomRepository) {
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
        this.roomRepository = roomRepository;
    }

    @Override
    @Transactional
    public ReviewResponse createReview(Long roomId, ReviewCreateRequest request) {
        AccommodationRooms room = roomRepository.findById(roomId)
            .orElseThrow(() -> new ReviewNotFoundException(ErrorCode.INVALID_ACCOMMODATION_ID));

        User user = userRepository.findById(request.getUserId())
            .orElseThrow(() -> new ReviewNotFoundException(ErrorCode.MEMBER_NOT_FOUND));

        Review review = Review.builder()
            .room(room)
            .user(user)
            .reviewContent(request.getReviewContent())
            .image(request.getImage())
            .star(request.getStar())
            .build();
        review.setRoom(room);
        review.setUser(user);
        review.setReviewContent(request.getReviewContent());
        review.setImage(request.getImage());
        review.setStar(request.getStar());

        Review savedReview = reviewRepository.save(review);

        return new ReviewResponse(
            savedReview.getReviewId(),
            roomId, user.getUserId(),
            savedReview.getReviewContent(),
            savedReview.getImage(),
            savedReview.getStar(),
            savedReview.getCreatedAt(),
            savedReview.getUpdatedAt());
    }

    @Override
    @Transactional(readOnly = true)
    public ReviewResponse getReview(Long reviewId) {
        Review review = reviewRepository.findById(reviewId)
            .orElseThrow(() -> new ReviewNotFoundException(ErrorCode.REVIEW_NOT_FOUND));

        // null 검사
        Long roomId = review.getRoom() != null ? review.getRoom().getRoomId() : null;
        Long userId = review.getUser() != null ? review.getUser().getUserId() : null;
        Long reservationId = (review.getReservation() != null) ? review.getReservation().getReservationId() : null;

        return new ReviewResponse(
            review.getReviewId(),
            roomId,
            userId,
            review.getReviewContent(),
            review.getImage(),
            review.getStar(),
            review.getCreatedAt(),
            review.getUpdatedAt());

    }

    @Override
    @Transactional
    public ReviewResponse updateReview(Long reviewId, ReviewUpdateRequest request) {
        Review review = reviewRepository.findById(reviewId)
            .orElseThrow(() -> new ReviewNotFoundException(ErrorCode.REVIEW_NOT_FOUND));

        review.setReviewContent(request.getReviewContent());
        review.setImage(request.getImage());
        review.setStar(request.getStar());

        return new ReviewResponse(
            review.getReviewId(),
            review.getRoom().getRoomId(),
            review.getUser().getUserId(),
            review.getReviewContent(),
            review.getImage(),
            review.getStar(),
            review.getCreatedAt(),
            review.getUpdatedAt());
    }

    @Override
    @Transactional
    public void deleteReview(Long reviewId) {
        Review review = reviewRepository.findById(reviewId)
            .orElseThrow(() -> new ReviewNotFoundException(ErrorCode.REVIEW_NOT_FOUND));

        if (!review.isActive()) {
            throw new ReviewNotFoundException(ErrorCode.REVIEW_NOT_FOUND);
        }

        reviewRepository.deleteById(reviewId);
    }

}
