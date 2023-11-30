package com.example.yanolja.domain.accommodation.dto;

import com.example.yanolja.domain.accommodation.entity.AccommodationRooms;
import java.util.Arrays;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoomsFindResponse {

  private Long roomId;
  private String name;
  private int price;
  private int discountPercentage;
  private String checkinExplanation;
  private int minCapacity;
  private int maxCapacity;
  private String tag;
  private String explanation;
  private String serviceInfo;

  @Builder
  public RoomsFindResponse(Long roomId, String name, int price, int discountPercentage,
      String checkinExplanation, int minCapacity, int maxCapacity, String tag, String explanation,
      String serviceInfo) {
    this.roomId = roomId;
    this.name = name;
    this.price = price;
    this.discountPercentage = discountPercentage;
    this.checkinExplanation = checkinExplanation;
    this.minCapacity = minCapacity;
    this.maxCapacity = maxCapacity;
    this.tag = tag;
    this.explanation = explanation;
    this.serviceInfo = serviceInfo;
  }


  public static RoomsFindResponse fromEntity(AccommodationRooms rooms){

    List<String> serviceList = Arrays.asList(rooms.getServiceInfo().split(","));

    return RoomsFindResponse.builder()
        .roomId(rooms.getRoomId())
        .name(rooms.getName())
        .price(rooms.getPrice())
        .discountPercentage(rooms.getDiscountPercentage())
        .checkinExplanation(rooms.getCheckinExplanation())
        .minCapacity(rooms.getMinCapacity())
        .maxCapacity(rooms.getMaxCapacity())
        .tag(rooms.getTag())
        .explanation(rooms.getExplanation())
        .serviceInfo(rooms.getServiceInfo())
        .build();
  }
}
