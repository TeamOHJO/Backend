package com.example.yanolja.domain.accommodation.service;

import com.example.yanolja.domain.accommodation.dto.AccommodationFindResponse;
import com.example.yanolja.domain.accommodation.entity.Accommodation;
import com.example.yanolja.domain.accommodation.entity.AccommodationCategory;
import com.example.yanolja.domain.accommodation.entity.AccommodationImages;
import com.example.yanolja.domain.accommodation.repository.AccommodationImageRepository;
import com.example.yanolja.domain.accommodation.repository.AccommodationRepository;
import com.example.yanolja.domain.accommodationLikes.entity.AccommodationLikes;
import com.example.yanolja.domain.accommodationLikes.repository.AccommodationLikesRepository;
import com.example.yanolja.domain.user.entity.User;
import com.example.yanolja.global.springsecurity.PrincipalDetails;
import jakarta.transaction.Transactional;
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

    @Transactional
    public Page<AccommodationFindResponse> getAllAccommodation(Pageable pageable) {
        Page<Accommodation> accommodations = accommodationRepository.findAll(pageable);
        return accommodations.map(this::convertToAccommodationFindResponse);
    }

    public AccommodationFindResponse getAccommodationById(Long accommodationId) {
        Accommodation foundAccommodation = accommodationRepository.findById(accommodationId)
            .orElseThrow();

        List<String> imageList = getImagesForAccommodation(accommodationId);
        return AccommodationFindResponse.fromEntity(foundAccommodation, imageList);
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
    public Page<AccommodationFindResponse> getAccommodationsByCategoryAndDomestic(
        PrincipalDetails principalDetails, String categoryStr,
        boolean isDomestic, Pageable pageable) {
        AccommodationCategory category = AccommodationCategory.valueOf(categoryStr.toUpperCase());

        if (principalDetails == null) {
            Page<Accommodation> accommodations = accommodationRepository.findByCategoryAndIsDomestic(
                category, isDomestic, pageable);
            return accommodations.map(this::convertToAccommodationFindResponse);

        } else {
            User user = principalDetails.getUser();

            Page<Accommodation> accommodations = accommodationRepository.findByCategoryAndIsDomestic(
                category, isDomestic, pageable);
            return accommodations.map(
                accommodation -> convertToAccommodationFindResponse(user, accommodation));

        }
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

    private AccommodationFindResponse convertToAccommodationFindResponse(
        User user,
        Accommodation accommodation) {
        List<String> imageList = getImagesForAccommodation(accommodation.getAccommodationId());
        List<String> serviceList = List.of(accommodation.getServiceInfo().split(","));
        boolean isLike = false;

        Optional<AccommodationLikes> accommodationLikesOptional =
            accommodationLikesRepository.findByUser_UserIdAndAccommodation_AccommodationId(
                user.getUserId(), accommodation.getAccommodationId()
            );

        if (accommodationLikesOptional.isPresent() &&
            accommodationLikesOptional.get().getIsLike()) {
            isLike = true;
        }

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
            .isLike(isLike)
            .build();
    }
}
