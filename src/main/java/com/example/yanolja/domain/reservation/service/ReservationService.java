package com.example.yanolja.domain.reservation.service;


import com.example.yanolja.domain.reservation.dto.CreateReservationRequest;
import com.example.yanolja.domain.reservation.dto.CreateReservationResponse;
import com.example.yanolja.domain.reservation.dto.GetReservationDetailsResponse;
import com.example.yanolja.domain.reservation.dto.GetUsersReservationResponse;
import com.example.yanolja.domain.user.entity.User;
import com.example.yanolja.global.util.ResponseDTO;
import java.util.List;

public interface ReservationService {

    ResponseDTO<CreateReservationResponse> createReservation(CreateReservationRequest createReservationRequest,
        User user, long roomId);

    ResponseDTO<GetReservationDetailsResponse> getReservationDetails(long roomId);

    ResponseDTO<Void> cancelReservation(User user, long reservationId);

    ResponseDTO<List<GetUsersReservationResponse>> getUsersReservation(User user);

    ResponseDTO<List<GetUsersReservationResponse>> getUsersCanceledReservation(User user);
}
