package com.example.yanolja.domain.accommodation.dto;

import com.example.yanolja.domain.accommodation.entity.Accommodation;
import com.example.yanolja.domain.accommodation.entity.AccommodationRooms;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.util.ArrayList;
import java.util.List;
import lombok.Builder;

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

  private String serviceinfo;

  @Builder
  public RoomsFindResponse(Long roomId, String name, int price, int discountPercentage,
      String checkinExplanation, int minCapacity, int maxCapacity, String tag, String explanation,
      String serviceinfo) {
    this.roomId = roomId;
    this.name = name;
    this.price = price;
    this.discountPercentage = discountPercentage;
    this.checkinExplanation = checkinExplanation;
    this.minCapacity = minCapacity;
    this.maxCapacity = maxCapacity;
    this.tag = tag;
    this.explanation = explanation;
    this.serviceinfo = serviceinfo;
  }


  public static RoomsFindResponse fromEntity(AccommodationRooms rooms){
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
        .build();  //todo serviceinfo 없음
  }
}
