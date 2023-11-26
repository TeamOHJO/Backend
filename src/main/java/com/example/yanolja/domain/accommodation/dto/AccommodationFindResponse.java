package com.example.yanolja.domain.accommodation.dto;

import com.example.yanolja.domain.accommodation.entity.Accommodation;
import com.example.yanolja.domain.accommodation.entity.AccommodationCategory;
import java.util.Arrays;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
public class AccommodationFindResponse {

    private Long accommodationId;
    private AccommodationCategory category;
    private String accommodationName;
    private String location;
    private String tag;
    private boolean isDomestic;
    private String explanation;
    private String cancelInfo;
    private String useGuide;
    private String reservationNotice;
    private List<String> serviceInfoList;
    private List<String> accommodationImageList;
    private double averageReviewScore; // 리뷰 평균 점수
    private boolean isLiked; // 좋아요 여부

    @Builder
    public AccommodationFindResponse(
        Long accommodationId, AccommodationCategory category, String accommodationName,
        String location, String tag, boolean isDomestic, String explanation, String cancelInfo,
        String useGuide, String reservationNotice, List<String> serviceInfoList,
        List<String> accommodationImageList, boolean isLike) {
        this.accommodationId = accommodationId;
        this.category = category;
        this.accommodationName = accommodationName;
        this.location = location;
        this.tag = tag;
        this.isDomestic = isDomestic;
        this.explanation = explanation;
        this.cancelInfo = cancelInfo;
        this.useGuide = useGuide;
        this.reservationNotice = reservationNotice;
        this.serviceInfoList = serviceInfoList;
        this.accommodationImageList = accommodationImageList;
        this.averageReviewScore = 4;
        this.isLiked = isLike;
    }

    public static AccommodationFindResponse fromEntity(Accommodation accommodation,
        List<String> imageList) {

        List<String> serviceList = Arrays.asList(
            accommodation.getServiceInfo().split(",")); // 콤마로 구분된 문자열을 리스트로 변환

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
            .build();
    }
}
