package com.example.yanolja.domain.accommodation.dto;

import com.example.yanolja.domain.accommodation.entity.Accommodation;
import com.example.yanolja.domain.accommodation.entity.AccommodationCategory;
import lombok.Builder;

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

    private String serviceInfo;

    @Builder
    public AccommodationFindResponse(
            Long accommodationId, AccommodationCategory category, String accommodationName,
            String location, String tag, boolean isDomestic, String explanation, String cancelInfo,
            String useGuide, String reservationNotice, String serviceInfo)
    {
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
        this.serviceInfo = serviceInfo;
    }


    public static AccommodationFindResponse fromEntity(Accommodation accommodation){

// todo accommodation rooms


        return AccommodationFindResponse.builder()
                .accommodationId(accommodation.getAccommodationId())
                .accommodationId(accommodation.getAccommodationId())
                .accommodationName(accommodation.getAccommodationName())
                .location(accommodation.getLocation())
                .tag(accommodation.getTag())
                .isDomestic(accommodation.isDomestic())
                .explanation(accommodation.getExplanation())
                .cancelInfo(accommodation.getCancelInfo())
                .useGuide(accommodation.getUseGuide())
                .reservationNotice(accommodation.getReservationNotice())
                .serviceInfo(accommodation.getServiceInfo())
                .build();
    }


}
