package com.example.yanolja.domain.accommodation.dto;

import com.example.yanolja.domain.accommodation.entity.AccommodationCategory;
import java.util.List;

public class AccommodationDetailResponse {

    private Long accommodationId;
    private String name;
    private String location;
    private String description;
    private AccommodationCategory category;
    private boolean isLiked;
    private double averageRating;
    private List<String> accommodationImages;
    private List<RoomDetail> roomDetails;

}
