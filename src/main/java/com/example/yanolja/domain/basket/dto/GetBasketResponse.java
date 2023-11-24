package com.example.yanolja.domain.basket.dto;

import com.example.yanolja.domain.accommodation.entity.Accommodation;
import com.example.yanolja.domain.accommodation.entity.AccommodationCategory;
import com.example.yanolja.domain.accommodation.entity.AccommodationRooms;
import com.example.yanolja.domain.reservation.entity.Reservations;
import java.time.LocalDate;

public record GetBasketResponse(
    Long roomId,
    String tag,
    AccommodationCategory category,
    String roomName,
    String roomInfo,
    LocalDate startDate,
    LocalDate endDate,
    String checkInOutExplanation,
    int star,
    int price,
    String image,
    boolean canReserve
) {

    public static GetBasketResponse fromEntity(Accommodation accommodation,
        Reservations reservations, AccommodationRooms rooms, String image,
        boolean canReserve) {
        return new GetBasketResponse(
            rooms.getRoomId(),
            rooms.getTag(),
            accommodation.getCategory(),
            rooms.getName(),
            rooms.getExplanation(),
            reservations.getStartDate(),
            reservations.getEndDate(),
            rooms.getCheckinExplanation(),
            4, //TODO ::: 리뷰별점 반영하여 점수 수정
            rooms.getPrice(),
            image,
            canReserve
        );
    }
}
