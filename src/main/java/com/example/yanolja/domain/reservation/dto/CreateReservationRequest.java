package com.example.yanolja.domain.reservation.dto;

import com.example.yanolja.domain.accommodation.entity.AccommodationRooms;
import com.example.yanolja.domain.reservation.entity.Reservations;
import com.example.yanolja.domain.user.entity.User;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;


public record CreateReservationRequest(
    @NotNull(message = "startDate cannot be null")
    LocalDate startDate,

    @NotNull(message = "endDate cannot be null")
    LocalDate endDate,

    @NotNull(message = "numberOfPerson cannot be null")
    Integer numberOfPerson
) {

    public Reservations toEntity(
        User user, AccommodationRooms rooms, boolean paymentCompleted) {
        return Reservations.builder()
            .user(user)
            .rooms(rooms)
            .startDate(startDate)
            .endDate(endDate)
            .numberOfPerson(numberOfPerson)
            .paymentCompleted(paymentCompleted)
            .build();
    }
}
