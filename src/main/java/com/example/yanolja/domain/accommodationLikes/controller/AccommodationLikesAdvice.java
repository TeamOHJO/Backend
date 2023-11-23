package com.example.yanolja.domain.accommodationLikes.controller;

import com.example.yanolja.domain.accommodationLikes.exception.UserIdAndAccommodationIdNotFoundException;
import com.example.yanolja.global.util.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AccommodationLikesAdvice {

    @ExceptionHandler(value = {
        UserIdAndAccommodationIdNotFoundException.class
    })
    public ResponseEntity<ResponseDTO<Object>> handleUserIdAndAccommodationIdNotFoundException(
        UserIdAndAccommodationIdNotFoundException exception
    ) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            ResponseDTO.res(HttpStatus.BAD_REQUEST,
                exception.getMessage()));
    }
}
