package com.example.yanolja.domain.accommodation.service;


import com.example.yanolja.domain.accommodation.dto.AccommodationDetailResponse;
import com.example.yanolja.domain.accommodation.dto.RoomDetail;
import com.example.yanolja.domain.accommodation.entity.Accommodation;
import com.example.yanolja.domain.accommodation.entity.AccommodationImages;
import com.example.yanolja.domain.accommodation.entity.AccommodationRoomImages;
import com.example.yanolja.domain.accommodation.entity.AccommodationRooms;
import com.example.yanolja.domain.accommodationLikes.entity.AccommodationLikes;
import com.example.yanolja.domain.accommodation.exception.AccommodationNotFoundException;
import com.example.yanolja.domain.accommodation.exception.RoomNotFoundException;
import com.example.yanolja.domain.accommodation.repository.AccommodationImageRepository;
import com.example.yanolja.domain.accommodation.repository.AccommodationRepository;
import com.example.yanolja.domain.accommodation.repository.AccommodationRoomImagesRepository;
import com.example.yanolja.domain.accommodation.repository.AccommodationRoomRepository;
import com.example.yanolja.domain.accommodationLikes.repository.AccommodationLikesRepository;
import com.example.yanolja.domain.reservation.repository.ReservationRepository;
import com.example.yanolja.domain.review.repository.ReviewRepository;
import com.example.yanolja.domain.review.entity.Review;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccommodationDetailsService {

    private final AccommodationRepository accommodationRepository;
    private final AccommodationImageRepository accommodationImageRepository;
    private final ReviewRepository reviewRepository;
    private final AccommodationRoomRepository accommodationRoomRepository;
    private final AccommodationRoomImagesRepository accommodationRoomImagesRepository;
    private final ReservationRepository reservationRepository;
    private final AccommodationLikesRepository accommodationLikesRepository;


    public AccommodationDetailResponse getAccommodationDetail(Long accommodationId, Long userId, int maxCapacity, LocalDate startDate, LocalDate endDate) {
        Accommodation accommodation = accommodationRepository.findById(accommodationId)
            .orElseThrow(AccommodationNotFoundException::new);

        List<AccommodationImages> accommodationImages = accommodationImageRepository.findByAccommodation_AccommodationId(accommodationId);

        double averageRating = reviewRepository.findByAccommodationId(accommodationId).stream()
            .mapToInt(Review::getStar)
            .average()
            .orElse(0.0);
        averageRating = Math.round(averageRating * 10) / 10.0;

        List<String> serviceInfoList = Arrays.asList(accommodation.getServiceInfo().split(","));

        List<RoomDetail> roomDetails = accommodation.getRoomlist().stream()
            .filter(room -> room.getMaxCapacity() >= maxCapacity)
            .map(room -> {
                boolean isSoldOut = reservationRepository.findConflictingReservations(room.getRoomId(), startDate, endDate).isPresent();
                RoomDetail roomDetail = getRoomDetail(room.getRoomId());

                return RoomDetail.builder()
                    .roomId(roomDetail.getRoomId())
                    .name(roomDetail.getName())
                    .price(roomDetail.getPrice())
                    .averageRating(roomDetail.getAverageRating())
                    .discountPercentage(roomDetail.getDiscountPercentage())
                    .checkinExplanation(roomDetail.getCheckinExplanation())
                    .minCapacity(roomDetail.getMinCapacity())
                    .maxCapacity(roomDetail.getMaxCapacity())
                    .tag(roomDetail.getTag())
                    .explanation(roomDetail.getExplanation())
                    .isSoldOut(isSoldOut)
                    .serviceInfo(roomDetail.getServiceInfo())
                    .roomImages(roomDetail.getRoomImages())
                    .build();
            })
            .collect(Collectors.toList());

        boolean isLiked = false;
        if (userId != null) {
            isLiked = accommodationLikesRepository.findByUser_UserIdAndAccommodation_AccommodationId(userId, accommodationId)
                .map(AccommodationLikes::getIsLike)
                .orElse(false);
        }

        return AccommodationDetailResponse.builder()
            .accommodationId(accommodation.getAccommodationId())
            .name(accommodation.getAccommodationName())
            .location(accommodation.getLocation())
            .isDomestic(accommodation.isDomestic())
            .category(accommodation.getCategory())
            .tag(accommodation.getTag())
            .description(accommodation.getExplanation())
            .explanation(accommodation.getExplanation())
            .cancelInfo(accommodation.getCancelInfo())
            .useGuide(accommodation.getUseGuide())
            .reservationNotice(accommodation.getReservationNotice())
            .serviceInfo(serviceInfoList)
            .averageRating(averageRating)
            .accommodationImages(accommodationImages.stream().map(AccommodationImages::getImage).collect(Collectors.toList()))
            .roomDetails(roomDetails)
            .isLiked(isLiked)
            .build();
    }

    public RoomDetail getRoomDetail(Long roomId) {
        AccommodationRooms room = accommodationRoomRepository.findById(roomId)
            .orElseThrow(RoomNotFoundException::new);

        List<Review> reviews = reviewRepository.findByRoom_RoomId(roomId);

        double averageRating = reviews.stream()
            .mapToInt(Review::getStar)
            .average()
            .orElse(0.0);
        averageRating = Math.round(averageRating * 10) / 10.0;

        List<AccommodationRoomImages> roomImages = accommodationRoomImagesRepository.findAllByAccommodationRoomsRoomId(
            room.getRoomId());

        List<String> imageUrls = roomImages.stream()
            .map(AccommodationRoomImages::getImage)
            .collect(Collectors.toList());

        return RoomDetail.builder()
            .roomId(room.getRoomId())
            .name(room.getName())
            .price(room.getPrice())
            .discountPercentage(room.getDiscountPercentage())
            .checkinExplanation(room.getCheckinExplanation())
            .minCapacity(room.getMinCapacity())
            .maxCapacity(room.getMaxCapacity())
            .tag(room.getTag())
            .explanation(room.getExplanation())
            .serviceInfo(room.getServiceInfo() != null ?
                Arrays.asList(room.getServiceInfo().split(",")) : Collections.emptyList())
            .roomImages(imageUrls)
            .averageRating(averageRating)
            .build();
    }
}