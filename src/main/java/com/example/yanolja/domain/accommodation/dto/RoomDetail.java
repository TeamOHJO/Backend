package com.example.yanolja.domain.accommodation.dto;

import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RoomDetail {

    private Long roomId;
    private String name;
    private int price;
    private double averageRating;
    private int discountPercentage;
    private String checkinExplanation;
    private int minCapacity;
    private int maxCapacity;
    private String tag;
    private String explanation;
    private List<String> serviceInfo;
    private List<String> roomImages;

}
