package com.example.yanolja.domain.basket.dto;

import com.example.yanolja.domain.accommodation.entity.AccommodationRooms;
import com.example.yanolja.domain.reservation.entity.Reservations;
import java.time.LocalDate;
import java.util.List;

public record GetBasketResponse(

    String tag,
    String roomName,
    String roomInfo,
    LocalDate startDate,
    LocalDate endDate,
    String checkInOutExplanation,
    int star,
    int price,
    List<String> image,
    boolean canReserve
) {

    public static GetBasketResponse fromEntity(
        Reservations reservations, AccommodationRooms rooms, List<String> image,
        boolean canReserve) {
        return new GetBasketResponse(
            rooms.getTag(),
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
