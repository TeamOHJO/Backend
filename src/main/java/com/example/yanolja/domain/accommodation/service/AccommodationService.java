package com.example.yanolja.domain.accommodation.service;

import com.example.yanolja.domain.accommodation.dto.AccommodationFindResponse;
import com.example.yanolja.domain.accommodation.entity.Accommodation;
import com.example.yanolja.domain.accommodation.entity.AccommodationCategory;
import com.example.yanolja.domain.accommodation.entity.AccommodationImages;
import com.example.yanolja.domain.accommodation.repository.AccommodationImageRepository;
import com.example.yanolja.domain.accommodation.repository.AccommodationRepository;
import com.example.yanolja.domain.accommodation.repository.AccommodationRepositoryCustom;
import com.example.yanolja.domain.accommodation.repository.AccommodationRoomRepository;
import com.example.yanolja.domain.accommodationLikes.entity.AccommodationLikes;
import com.example.yanolja.domain.accommodationLikes.repository.AccommodationLikesRepository;
import com.example.yanolja.domain.reservation.repository.ReservationRepository;
import com.example.yanolja.domain.review.entity.Review;
import com.example.yanolja.domain.review.repository.ReviewRepository;
import com.example.yanolja.domain.user.entity.User;
import com.example.yanolja.global.springsecurity.PrincipalDetails;
import jakarta.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AccommodationService {

    private final AccommodationRepository accommodationRepository;
    private final AccommodationImageRepository accommodationImageRepository;
    private final AccommodationLikesRepository accommodationLikesRepository;
    private final ReservationRepository reservationRepository;
    private final AccommodationRoomRepository accommodationRoomRepository;
    private final ReviewRepository reviewRepository;
    private final AccommodationRepositoryCustom accommodationRepositoryCustom;

    @Transactional
    public Page<AccommodationFindResponse> getAllAccommodation(Pageable pageable) {
        Page<Accommodation> accommodations = accommodationRepository.findAll(pageable);
        return accommodations.map(this::convertToAccommodationFindResponse);
    }

    @Transactional
    public Page<AccommodationFindResponse> getAccommodationsByCategory(String categoryStr,
        Pageable pageable) {
        AccommodationCategory category = AccommodationCategory.valueOf(categoryStr.toUpperCase());
        Page<Accommodation> accommodations = accommodationRepository.findByCategory(category,
            pageable);
        return accommodations.map(this::convertToAccommodationFindResponse);
    }

    @Transactional
    public Page<AccommodationFindResponse> getAccommodationsByDomestic(boolean isDomestic,
        Pageable pageable) {
        Page<Accommodation> accommodations = accommodationRepository.findByIsDomestic(isDomestic,
            pageable);
        return accommodations.map(this::convertToAccommodationFindResponse);
    }

    @Transactional
    public List<AccommodationFindResponse> getAccommodationsInMainPage(
        PrincipalDetails principalDetails, AccommodationCategory category,
        boolean isDomestic, Pageable pageable, LocalDate startDate, LocalDate endDate,
        int numberOfPeople) {
        Page<Long> acIds = accommodationRepositoryCustom.findAccommodationIds(
            category, isDomestic, pageable, startDate, endDate, numberOfPeople);

        List<Accommodation> accommodationList =
            acIds.stream().map(accommodationRepository::findByAccommodationId).toList();

        double averageRating = 0;
        List<AccommodationFindResponse> accommodationFindResponses = new ArrayList<>();
        if (principalDetails == null) {
            for (Accommodation accommodationContent : accommodationList) {
                averageRating = reviewRepository.findByAccommodationId(
                        accommodationContent.getAccommodationId()).stream()
                    .mapToInt(Review::getStar)
                    .average()
                    .orElse(0.0);
                averageRating = Math.round(averageRating * 10) / 10.0;

                accommodationFindResponses.add(
                    AccommodationFindResponse.fromEntity(
                        accommodationContent,
                        getImagesForAccommodation(accommodationContent.getAccommodationId()),
                        false,
                        accommodationRoomRepository.selectMinPrice(
                            accommodationContent.getAccommodationId()),
                        averageRating
                    )
                );
            }
        } else {
            User user = principalDetails.getUser();
            for (Accommodation accommodationContent : accommodationList) {
                boolean isLike = false;
                Optional<AccommodationLikes> accommodationLikesOptional =
                    accommodationLikesRepository.findByUser_UserIdAndAccommodation_AccommodationId(
                        user.getUserId(), accommodationContent.getAccommodationId()
                    );
                if (accommodationLikesOptional.isPresent() &&
                    accommodationLikesOptional.get().getIsLike()) {
                    isLike = true;
                }

                averageRating = reviewRepository.findByAccommodationId(
                        accommodationContent.getAccommodationId()).stream()
                    .mapToInt(Review::getStar)
                    .average()
                    .orElse(0.0);
                averageRating = Math.round(averageRating * 10) / 10.0;

                accommodationFindResponses.add(
                    AccommodationFindResponse.fromEntity(
                        accommodationContent,
                        getImagesForAccommodation(accommodationContent.getAccommodationId()),
                        isLike,
                        accommodationRoomRepository.selectMinPrice(
                            accommodationContent.getAccommodationId()),
                        averageRating
                    )
                );
            }
        }
        return accommodationFindResponses;
    }

    //숙소 별 이미지 리스트(String) 조회 모듈
    private List<String> getImagesForAccommodation(Long accommodationId) {
        List<AccommodationImages> images = accommodationImageRepository.findByAccommodation_AccommodationId(
            accommodationId);
        List<String> imageList = new ArrayList<>();
        for (AccommodationImages image : images) {
            imageList.add(image.getImage());
        }
        return imageList;
    }

    // Accommodation 엔티티를 AccommodationFindResponse DTO로 변환
    private AccommodationFindResponse convertToAccommodationFindResponse(
        Accommodation accommodation) {
        List<String> imageList = getImagesForAccommodation(accommodation.getAccommodationId());
        List<String> serviceList = List.of(accommodation.getServiceInfo().split(","));

        return AccommodationFindResponse.builder()
            .accommodationId(accommodation.getAccommodationId())
            .category(accommodation.getCategory())
            .accommodationName(accommodation.getAccommodationName())
            .location(accommodation.getLocation())
            .tag(accommodation.getTag())
            .isDomestic(accommodation.isDomestic())
            .explanation(accommodation.getExplanation())
            .cancelInfo(accommodation.getCancelInfo())
            .useGuide(accommodation.getUseGuide())
            .reservationNotice(accommodation.getReservationNotice())
            .serviceInfoList(serviceList)
            .accommodationImageList(imageList)
            .isLike(false)
            .build();
    }
}
