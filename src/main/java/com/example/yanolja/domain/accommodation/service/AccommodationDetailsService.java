package com.example.yanolja.domain.accommodation.service;

import com.example.yanolja.domain.accommodation.dto.AccommodationDetailResponse;
import com.example.yanolja.domain.accommodation.entity.Accommodation;
import com.example.yanolja.domain.accommodation.entity.AccommodationImages;
import com.example.yanolja.domain.accommodation.entity.AccommodationRooms;
import com.example.yanolja.domain.accommodation.exception.AccommodationNotFoundException;
import com.example.yanolja.domain.accommodation.repository.AccommodationImageRepository;
import com.example.yanolja.domain.accommodation.repository.AccommodationRepository;
import com.example.yanolja.domain.accommodation.repository.AccommodationRoomImagesRepository;
import com.example.yanolja.domain.accommodation.repository.AccommodationRoomRepository;
import com.example.yanolja.domain.accommodationLikes.repository.AccommodationLikesRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccommodationDetailsService {
    private final AccommodationRepository accommodationRepository;
    private final AccommodationImageRepository accommodationImageRepository;
    private final AccommodationRoomRepository accommodationRoomRepository;
    private final AccommodationRoomImagesRepository accommodationRoomImagesRepository;
    private final AccommodationLikesRepository accommodationLikesRepository;
    private final ReviewRepository reviewRepository;
    // 기타 필요한 Repository

    public AccommodationDetailResponse getAccommodationDetail(Long accommodationId, Long userId) {
        Accommodation accommodation = accommodationRepository.findById(accommodationId)
            .orElseThrow(() -> new AccommodationNotFoundException("Accommodation not found"));

        List<AccommodationImages> accommodationImages = accommodationImageRepository.findByAccommodation_AccommodationId(accommodationId);
        List<AccommodationRooms> rooms = accommodationRoomRepository.findAllByAccommodationId(accommodationId);
        boolean isLiked = accommodationLikesRepository.findByUser_UserIdAndAccommodation_AccommodationId(userId, accommodationId)
            .isPresent();

        // 평균 별점 계산
        double averageRating = reviewRepository.findByRoom_Accommodation_AccommodationId(accommodationId).stream()
            .mapToInt(Review::getStar)
            .average()
            .orElse(0.0);

        return new AccommodationDetailResponse(accommodation, accommodationImages, rooms, isLiked, averageRating);
    }
}



