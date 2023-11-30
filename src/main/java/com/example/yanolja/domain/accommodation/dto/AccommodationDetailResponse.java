package com.example.yanolja.domain.accommodation.dto;

import com.example.yanolja.domain.accommodation.entity.AccommodationCategory;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AccommodationDetailResponse {

    private Long accommodationId;
    private String name;
    private String location;
    private boolean isDomestic;
    private AccommodationCategory category;
    private double averageRating;
    private String tag;
    private String description;
    private String explanation;
    private String cancelInfo;
    private String useGuide;
    private String reservationNotice;
    private boolean isLiked;
    private List<String> accommodationImages;
    private List<String> serviceInfo;
    private List<RoomDetail> roomDetails;


}
