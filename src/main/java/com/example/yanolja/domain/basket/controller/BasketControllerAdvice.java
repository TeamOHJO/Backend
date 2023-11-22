package com.example.yanolja.domain.basket.controller;

import com.example.yanolja.domain.basket.error.DuplicatedBasketException;
import com.example.yanolja.global.util.ResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class BasketControllerAdvice {

    @ExceptionHandler(value = {
        DuplicatedBasketException.class
    })
    public ResponseEntity<ResponseDTO<Object>> handleDuplicatedBasketException(
        DuplicatedBasketException exception
    ) {
        return ResponseEntity.status(exception.getErrorCode().getHttpStatus()).body(
            ResponseDTO.res(exception.getErrorCode().getHttpStatus(),
                exception.getMessage()));
    }
}
