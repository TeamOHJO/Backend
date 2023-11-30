package com.example.yanolja.domain.accommodation.controller;

import com.example.yanolja.domain.accommodation.exception.AccommodationNotFoundException;
import com.example.yanolja.domain.accommodation.exception.RoomNotFoundException;
import com.example.yanolja.global.util.ResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AccommodationControllerAdvice {

    @ExceptionHandler(AccommodationNotFoundException.class)
    public ResponseEntity<ResponseDTO<Object>> handleAccommodationNotFound(
        AccommodationNotFoundException exception) {
        return ResponseEntity
            .status(exception.getErrorCode().getHttpStatus())
            .body(
                ResponseDTO.res(exception.getErrorCode().getHttpStatus(), exception.getMessage()));
    }

    @ExceptionHandler(RoomNotFoundException.class)
    public ResponseEntity<ResponseDTO<Object>> handleRoomNotFound(RoomNotFoundException exception) {
        return ResponseEntity
            .status(exception.getErrorCode().getHttpStatus())
            .body(
                ResponseDTO.res(exception.getErrorCode().getHttpStatus(), exception.getMessage()));
    }

}
