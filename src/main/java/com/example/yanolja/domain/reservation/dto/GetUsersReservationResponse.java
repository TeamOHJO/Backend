package com.example.yanolja.domain.reservation.dto;

import com.example.yanolja.domain.accommodation.entity.Accommodation;
import com.example.yanolja.domain.accommodation.entity.AccommodationCategory;
import com.example.yanolja.domain.accommodation.entity.AccommodationRooms;
import com.example.yanolja.domain.reservation.entity.Reservations;
import java.time.LocalDate;

public record GetUsersReservationResponse(
    Long roomId,
    AccommodationCategory category,
    String accommodationName,
    String roomName,
    String location,
    double star,
    String image,
    LocalDate startDate,
    LocalDate endDate
) {

    public static GetUsersReservationResponse fromEntity(Accommodation accommodation,
        AccommodationRooms rooms, String image, Reservations reservations, double star) {
        return new GetUsersReservationResponse(
            rooms.getRoomId(),
            accommodation.getCategory(),
            accommodation.getAccommodationName(),
            rooms.getName(),
            accommodation.getLocation(),
            star,
            image,
            reservations.getStartDate(),
            reservations.getEndDate()
        );
    }
}
