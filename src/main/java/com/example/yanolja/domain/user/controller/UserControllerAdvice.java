package com.example.yanolja.domain.user.controller;

import com.example.yanolja.domain.user.error.EmailDuplicateError;
import com.example.yanolja.domain.user.error.InvalidEmailException;
import com.example.yanolja.domain.user.error.InvalidPhonenumberException;
import com.example.yanolja.global.util.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserControllerAdvice {

    @ExceptionHandler(value = {
        EmailDuplicateError.class
    })
    public ResponseEntity<ResponseDTO<Object>> handleEmailDuplicateError(
        EmailDuplicateError exception
    ) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            ResponseDTO.res(HttpStatus.BAD_REQUEST,
                exception.getMessage()));
    }

    @ExceptionHandler(value = {
        InvalidEmailException.class
    })
    public ResponseEntity<ResponseDTO<Object>> handleInvalidEmailException(
        InvalidEmailException exception
    ) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            ResponseDTO.res(HttpStatus.BAD_REQUEST,
                exception.getMessage()));
    }

    @ExceptionHandler(value = {
        InvalidPhonenumberException.class
    })
    public ResponseEntity<ResponseDTO<Object>> handlePhonenumberException(
        InvalidPhonenumberException exception
    ) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            ResponseDTO.res(HttpStatus.BAD_REQUEST,
                exception.getMessage()));
    }
}
