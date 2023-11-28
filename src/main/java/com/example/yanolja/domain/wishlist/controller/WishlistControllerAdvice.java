package com.example.yanolja.domain.wishlist.controller;

import com.example.yanolja.domain.user.exception.UserNotFoundException;
import com.example.yanolja.global.util.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class WishlistControllerAdvice {

    @ExceptionHandler(value = {
        UserNotFoundException.class
    })
    public ResponseEntity<ResponseDTO<Object>> handleUserNotFoundException(
        UserNotFoundException exception
    ) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
            ResponseDTO.res(HttpStatus.NOT_FOUND, exception.getMessage()));
    }

}
