package com.example.yanolja.domain.basket.dto;

import com.example.yanolja.domain.accommodation.entity.Accommodation;
import com.example.yanolja.domain.accommodation.entity.AccommodationCategory;
import com.example.yanolja.domain.accommodation.entity.AccommodationRooms;
import com.example.yanolja.domain.basket.entity.Basket;
import com.example.yanolja.domain.reservation.entity.Reservations;
import java.time.LocalDate;

public record GetBasketResponse(
    Long basketId,
    String accommodationName,
    Long roomId,
    String tag,
    AccommodationCategory category,
    String roomName,
    String roomInfo,
    LocalDate startDate,
    LocalDate endDate,
    String checkInOutExplanation,
    double star,
    int price,
    int discountPercentage,
    String image,
    boolean canReserve,
    int numberOfPerson,
    String location
) {

    public static GetBasketResponse fromEntity(Basket basket, Accommodation accommodation,
        Reservations reservations, AccommodationRooms rooms, String image,
        boolean canReserve, double averageStar) {
        return new GetBasketResponse(
            basket.getBasketId(),
            accommodation.getAccommodationName(),
            rooms.getRoomId(),
            rooms.getTag(),
            accommodation.getCategory(),
            rooms.getName(),
            rooms.getExplanation(),
            reservations.getStartDate(),
            reservations.getEndDate(),
            rooms.getCheckinExplanation(),
            averageStar,
            rooms.getPrice(),
            rooms.getDiscountPercentage(),
            image,
            canReserve,
            reservations.getNumberOfPerson(),
            accommodation.getLocation()
        );
    }
}
