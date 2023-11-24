package com.example.yanolja.domain.reservation.dto;

import com.example.yanolja.domain.accommodation.entity.Accommodation;
import com.example.yanolja.domain.accommodation.entity.AccommodationCategory;
import com.example.yanolja.domain.accommodation.entity.AccommodationRooms;

public record GetReservationDetailsResponse(
    Long roomId,
    AccommodationCategory category,
    String roomName,
    String location,
    int star,
    int price,
    int discountPercentage,
    int discountPrice,
    String image
) {

    public static GetReservationDetailsResponse fromEntity(Accommodation accommodation,
        AccommodationRooms rooms, String image) {
        return new GetReservationDetailsResponse(
            rooms.getRoomId(),
            accommodation.getCategory(),
            rooms.getName(),
            accommodation.getLocation(),
            4, //TODO ::: 리뷰별점 반영하여 점수 수정
            rooms.getPrice(),
            rooms.getDiscountPercentage(),
            (int) (rooms.getPrice() * rooms.getDiscountPercentage() * 0.01),
            image
        );
    }
}
