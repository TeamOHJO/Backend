package com.example.yanolja.domain.review.service;

import com.example.yanolja.domain.accommodation.entity.AccommodationCategory;
import com.example.yanolja.domain.accommodation.entity.AccommodationRooms;
import com.example.yanolja.domain.accommodation.repository.AccommodationRoomRepository;
import com.example.yanolja.domain.review.dto.AccommodationReviewResponse;
import com.example.yanolja.domain.review.dto.ReviewCreateRequest;
import com.example.yanolja.domain.review.dto.ReviewUpdateRequest;
import com.example.yanolja.domain.review.dto.RoomReviewResponse;
import com.example.yanolja.domain.review.entity.Review;
import com.example.yanolja.domain.review.error.ReviewNotFoundException;
import com.example.yanolja.domain.review.repository.ReviewRepository;
import com.example.yanolja.domain.user.entity.User;
import com.example.yanolja.domain.user.repository.UserRepository;
import com.example.yanolja.global.exception.ErrorCode;
import java.util.List;
import java.util.stream.Collectors;
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
    public RoomReviewResponse createReview(Long roomId, ReviewCreateRequest request) {
        AccommodationRooms room = roomRepository.findById(roomId)
            .orElseThrow(() -> new ReviewNotFoundException(ErrorCode.INVALID_ACCOMMODATION_ID));

        User user = userRepository.findById(request.getUserId())
            .orElseThrow(() -> new ReviewNotFoundException(ErrorCode.USER_NOT_FOUND));

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

        return new RoomReviewResponse(
            savedReview.getReviewId(),
            roomId,
            user.getUsername(),
            room.getName(),
            room.getAccommodation().getCategory(),
            savedReview.getReviewContent(),
            savedReview.getImage(),
            savedReview.getStar(),
            savedReview.getUpdatedAt());
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
    public List<RoomReviewResponse> getReviewsByRoomId(Long roomId) {
        return reviewRepository.findByRoom_RoomId(roomId).stream()
            .map(review -> new RoomReviewResponse(
                review.getReviewId(),
                review.getRoom().getRoomId(),
                review.getUser().getUsername(),
                review.getRoom().getName(),
                review.getRoom().getAccommodation().getCategory(),
                review.getReviewContent(),
                review.getImage(),
                review.getStar(),
                review.getUpdatedAt()))
            .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public RoomReviewResponse updateReview(Long reviewId, ReviewUpdateRequest request) {
        Review review = reviewRepository.findById(reviewId)
            .orElseThrow(() -> new ReviewNotFoundException(ErrorCode.REVIEW_NOT_FOUND));

        review.setReviewContent(request.getReviewContent());
        review.setImage(request.getImage());
        review.setStar(request.getStar());

        String username = review.getUser().getUsername();
        String roomName = review.getRoom().getName();
        AccommodationCategory category = review.getRoom().getAccommodation().getCategory();

        return new RoomReviewResponse(
            review.getReviewId(),
            review.getRoom().getRoomId(),
            username,
            roomName,
            category,
            review.getReviewContent(),
            review.getImage(),
            review.getStar(),
            review.getUpdatedAt());
    }

    @Override
    @Transactional
    public void deleteReview(Long reviewId) {
        Review review = reviewRepository.findById(reviewId)
            .orElseThrow(() -> new ReviewNotFoundException(ErrorCode.REVIEW_NOT_FOUND));

        reviewRepository.delete(review);
    }

}
