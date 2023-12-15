package com.example.yanolja.domain.reservation.controller;

import com.example.yanolja.domain.reservation.dto.CreateReservationRequest;
import com.example.yanolja.domain.reservation.dto.CreateReservationResponse;
import com.example.yanolja.domain.reservation.dto.GetReservationDetailsResponse;
import com.example.yanolja.domain.reservation.dto.GetUsersReservationResponse;
import com.example.yanolja.domain.reservation.service.ReservationService;
import com.example.yanolja.domain.user.entity.User;
import com.example.yanolja.global.springsecurity.PrincipalDetails;
import com.example.yanolja.global.util.ResponseDTO;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reservation")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping("/rooms/{roomId}")
    public ResponseEntity<ResponseDTO<CreateReservationResponse>> createReservation(
        @PathVariable long roomId,
        @AuthenticationPrincipal PrincipalDetails principalDetails,
        @Valid @RequestBody CreateReservationRequest createReservationRequest) {
        User user = principalDetails.getUser();

        ResponseDTO<CreateReservationResponse> response = reservationService.createReservation(
            createReservationRequest, user, roomId);
        return ResponseEntity.status(response.getCode()).body(response);
    }

    @GetMapping("/details/rooms/{roomId}")
    public ResponseEntity<ResponseDTO<GetReservationDetailsResponse>> getReservationDetails(
        @PathVariable long roomId) {

        ResponseDTO<GetReservationDetailsResponse> response = reservationService.getReservationDetails(
            roomId);
        return ResponseEntity.status(response.getCode()).body(response);
    }

    @DeleteMapping("/{reservationId}")
    public ResponseEntity<ResponseDTO<Void>> cancelReservation(
        @PathVariable long reservationId,
        @AuthenticationPrincipal PrincipalDetails principalDetails
    ) {

        User user = principalDetails.getUser();
        ResponseDTO<Void> response = reservationService.cancelReservation(user, reservationId);
        return ResponseEntity.status(response.getCode()).body(response);
    }

    //유저의 예약 내역 조회
    @GetMapping("")
    public ResponseEntity<ResponseDTO<List<GetUsersReservationResponse>>> getUsersReservation(
        @AuthenticationPrincipal PrincipalDetails principalDetails) {
        User user = principalDetails.getUser();

        ResponseDTO<List<GetUsersReservationResponse>> response
            = reservationService.getUsersReservation(user);
        return ResponseEntity.status(response.getCode()).body(response);
    }

    @GetMapping("/canceled")
    public ResponseEntity<ResponseDTO<List<GetUsersReservationResponse>>> getUsersCanceledReservation(
        @AuthenticationPrincipal PrincipalDetails principalDetails) {
        User user = principalDetails.getUser();

        ResponseDTO<List<GetUsersReservationResponse>> response =
            reservationService.getUsersCanceledReservation(user);
        return ResponseEntity.status(response.getCode()).body(response);
    }
}
