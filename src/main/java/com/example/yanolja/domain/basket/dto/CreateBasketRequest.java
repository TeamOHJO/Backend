package com.example.yanolja.domain.basket.dto;

import com.example.yanolja.domain.accommodation.entity.AccommodationRooms;
import com.example.yanolja.domain.basket.entity.Basket;
import com.example.yanolja.domain.reservation.entity.Reservations;
import com.example.yanolja.domain.user.entity.User;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;


public record CreateBasketRequest(

    @NotNull(message = "startDate cannot be null")
    LocalDate startDate,

    @NotNull(message = "endDate cannot be null")
    LocalDate endDate,

    @NotNull(message = "numberOfPerson cannot be null")
    Integer numberOfPerson

) {

    public Basket toEntity(
        User user, AccommodationRooms rooms, Reservations reservations) {
        return Basket.builder()
            .user(user)
            .room(rooms)
            .reservation(reservations)
            .build();
    }
}
