package com.example.yanolja.domain.accommodation.dto;

import com.example.yanolja.domain.accommodation.entity.Accommodation;
import com.example.yanolja.domain.accommodation.entity.AccommodationCategory;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class AccommodationListFindResponse {

    private Long accommodationId;

//    @Enumerated(EnumType.STRING)
//    private AccomodationCategory category; //todo enum활용, builder 쪽도
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
    public AccommodationListFindResponse(
            Long accommodationId, AccommodationCategory category, String accommodationName, String location,
            String tag, boolean isDomestic, String explanation, String cancelInfo, String useGuide,
            String reservationNotice, String serviceInfo)
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


    private List<AccommodationListFindResponse> listToResponse(List<Accommodation> accommodationList){
        List<AccommodationListFindResponse> responseList = new ArrayList<>();
        for(Accommodation found : accommodationList){

            AccommodationListFindResponse accommodatonListFindResponse = AccommodationListFindResponse.builder()
                    .accommodationId(found.getAccommodationId())
                    .accommodationId(found.getAccommodationId())
                    .accommodationName(found.getAccommodationName())
                    .location(found.getLocation())
                    .tag(found.getTag())
                    .isDomestic(found.isDomestic())
                    .explanation(found.getExplanation())
                    .cancelInfo(found.getCancelInfo())
                    .useGuide(found.getUseGuide())
                    .reservationNotice(found.getReservationNotice())
                    .serviceInfo(found.getServiceInfo())
                    .build();

            responseList.add(accommodatonListFindResponse);
        }
        return responseList;
    }

}
