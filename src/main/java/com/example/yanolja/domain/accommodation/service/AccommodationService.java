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
import com.example.yanolja.domain.review.repository.ReviewRepository;
import com.example.yanolja.domain.user.entity.User;
import com.example.yanolja.global.springsecurity.PrincipalDetails;
import com.example.yanolja.global.util.ResponseDTO;
import com.example.yanolja.global.util.ReviewRatingUtils;
import jakarta.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AccommodationService {

    private final AccommodationRepository accommodationRepository;
    private final AccommodationImageRepository accommodationImageRepository;
    private final AccommodationLikesRepository accommodationLikesRepository;
    private final AccommodationRoomRepository accommodationRoomRepository;
    private final ReviewRepository reviewRepository;
    private final AccommodationRepositoryCustom accommodationRepositoryCustom;

    @Transactional
    public ResponseDTO<List<AccommodationFindResponse>> getAccommodationsInMainPage(
        PrincipalDetails principalDetails, AccommodationCategory category,
        boolean isDomestic, Pageable pageable, LocalDate startDate, LocalDate endDate,
        int numberOfPeople) {
        Page<Long> acIds = accommodationRepositoryCustom.findAccommodationIds(
            category, isDomestic, pageable, startDate, endDate, numberOfPeople);

        List<Accommodation> accommodationList =
            acIds.stream().map(accommodationRepository::findByAccommodationId).toList();

        List<AccommodationFindResponse> accommodationFindResponses = new ArrayList<>();

        accommodationList.stream()
            .map(accommodationContent -> {
                boolean isLike = false;
                if (principalDetails != null) {
                    User user = principalDetails.getUser();
                    Optional<AccommodationLikes> accommodationLikesOptional =
                        accommodationLikesRepository.findByUser_UserIdAndAccommodation_AccommodationId(
                            user.getUserId(), accommodationContent.getAccommodationId()
                        );
                    isLike = accommodationLikesOptional.map(AccommodationLikes::getIsLike)
                        .orElse(false);
                }
                return createAccommodationResponse(accommodationContent, isLike);
            })
            .forEach(accommodationFindResponses::add);

        return ResponseDTO.res(HttpStatus.OK, "카테고리와 국내/국외별 숙소 조회 성공", accommodationFindResponses);
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

    private AccommodationFindResponse createAccommodationResponse(
        Accommodation accommodationContent,
        boolean isLike) {
        return AccommodationFindResponse.fromEntity(
            accommodationContent,
            getImagesForAccommodation(accommodationContent.getAccommodationId()),
            isLike,
            accommodationRoomRepository.selectMinPrice(accommodationContent.getAccommodationId()),
            ReviewRatingUtils.calculateAverageRating(accommodationContent.getAccommodationId(),
                reviewRepository)
        );
    }
}
