package com.example.yanolja.domain.user.controller;

import com.example.yanolja.domain.user.exception.EmailDuplicateError;
import com.example.yanolja.domain.user.exception.InvalidEmailException;
import com.example.yanolja.domain.user.exception.InvalidPasswordException;
import com.example.yanolja.domain.user.exception.InvalidPhonenumberError;
import com.example.yanolja.domain.user.exception.UserNotFoundException;
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
        return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(
            ResponseDTO.res(HttpStatus.PRECONDITION_FAILED,
                exception.getMessage()));
    }

    @ExceptionHandler(value = {
        InvalidPhonenumberError.class
    })
    public ResponseEntity<ResponseDTO<Object>> handlePhonenumberException(
        InvalidPhonenumberError exception
    ) {
        return ResponseEntity.status(HttpStatus.LENGTH_REQUIRED).body(
            ResponseDTO.res(HttpStatus.LENGTH_REQUIRED,
                exception.getMessage()));
    }

    @ExceptionHandler(value = {
        UserNotFoundException.class
    })
    public ResponseEntity<ResponseDTO<Object>> handleUserNotFoundException(
        UserNotFoundException exception
    ) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
            ResponseDTO.res(HttpStatus.NOT_FOUND, exception.getMessage()));
    }

    @ExceptionHandler(value = InvalidPasswordException.class)
    public ResponseEntity<ResponseDTO<Object>> handleInvalidPasswordException(
        InvalidPasswordException exception) {
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(ResponseDTO.res(HttpStatus.BAD_REQUEST, exception.getMessage()));
    }


}

