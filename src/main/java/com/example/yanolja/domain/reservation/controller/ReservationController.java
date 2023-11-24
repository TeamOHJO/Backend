package com.example.yanolja.domain.reservation.controller;

import com.example.yanolja.domain.reservation.dto.CreateReservationRequest;
import com.example.yanolja.domain.reservation.service.ReservationService;
import com.example.yanolja.domain.user.entity.User;
import com.example.yanolja.global.springsecurity.PrincipalDetails;
import com.example.yanolja.global.util.ResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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

    @PostMapping("/{roomsId}")
    public ResponseEntity<ResponseDTO<?>> createReservation(
        @PathVariable long roomsId,
        @AuthenticationPrincipal PrincipalDetails principalDetails,
        @Valid @RequestBody CreateReservationRequest createReservationRequest) {
        User user = principalDetails.getUser();

        ResponseDTO<?> response = reservationService.createReservation(
            createReservationRequest, user, roomsId);
        return ResponseEntity.status(response.getCode()).body(response);
    }
}
