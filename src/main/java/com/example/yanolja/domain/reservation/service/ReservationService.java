package com.example.yanolja.domain.reservation.service;


import com.example.yanolja.domain.reservation.dto.CreateReservationRequest;
import com.example.yanolja.domain.user.entity.User;
import com.example.yanolja.global.util.ResponseDTO;

public interface ReservationService {

    ResponseDTO<?> createReservation(CreateReservationRequest createReservationRequest,
        User user, long roomId);

    ResponseDTO<?> getReservationDetails(long roomId);
}
