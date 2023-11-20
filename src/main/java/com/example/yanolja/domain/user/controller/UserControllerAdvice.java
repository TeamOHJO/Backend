package com.example.yanolja.domain.user.controller;

import com.example.yanolja.domain.user.error.EmailDuplicateError;
import com.example.yanolja.domain.user.error.InvalidEmailException;
import com.example.yanolja.global.exception.ErrorMessage;
import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserControllerAdvice {

    @ExceptionHandler(value = {
        EmailDuplicateError.class
    })
    public ResponseEntity<ErrorMessage> handleEmailDuplicateError(
        EmailDuplicateError exception
    ) {
        ErrorMessage errorMessage = new ErrorMessage(
            exception.getMessage(),
            LocalDateTime.now());
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {
        InvalidEmailException.class
    })
    public ResponseEntity<ErrorMessage> handleInvalidEmailException(
        InvalidEmailException exception
    ) {
        ErrorMessage errorMessage = new ErrorMessage(
            exception.getMessage(),
            LocalDateTime.now());
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }
}
