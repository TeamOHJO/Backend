package com.example.yanolja.domain.reservation.controller;

import com.example.yanolja.domain.reservation.exception.InvalidAccommodationRoomIdException;
import com.example.yanolja.domain.reservation.exception.InvalidReservationException;
import com.example.yanolja.global.util.ResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ReservationControllerAdvice {

    @ExceptionHandler(value = {
        InvalidAccommodationRoomIdException.class
    })
    public ResponseEntity<ResponseDTO<Object>> handleInvalidAccommodationRoomIdException(
        InvalidAccommodationRoomIdException exception
    ) {
        return ResponseEntity.status(exception.getErrorCode().getHttpStatus()).body(
            ResponseDTO.res(exception.getErrorCode().getHttpStatus(),
                exception.getMessage()));
    }

    @ExceptionHandler(value = {
        InvalidReservationException.class
    })
    public ResponseEntity<ResponseDTO<Object>> handleInvalidReservationException(
        InvalidReservationException exception
    ) {
        return ResponseEntity.status(exception.getErrorCode().getHttpStatus()).body(
            ResponseDTO.res(exception.getErrorCode().getHttpStatus(),
                exception.getMessage()));
    }
}
