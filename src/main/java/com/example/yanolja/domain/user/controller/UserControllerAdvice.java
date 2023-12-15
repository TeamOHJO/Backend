package com.example.yanolja.domain.user.controller;

import com.example.yanolja.domain.user.exception.EmailDuplicateException;
import com.example.yanolja.domain.user.exception.EmailSendingException;
import com.example.yanolja.domain.user.exception.EmailTemplateLoadException;
import com.example.yanolja.domain.user.exception.InvalidPasswordException;
import com.example.yanolja.domain.user.exception.UserNotFoundException;
import com.example.yanolja.global.util.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserControllerAdvice {

    @ExceptionHandler(value = {
        EmailDuplicateException.class,
        EmailSendingException.class,
        EmailTemplateLoadException.class,
        InvalidPasswordException.class,
        UserNotFoundException.class
    })
    public ResponseEntity<ResponseDTO<Object>> handleUserControllerExceptions(
        RuntimeException exception) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        if (exception instanceof EmailDuplicateException) {
            status = HttpStatus.BAD_REQUEST;
        } else if (exception instanceof EmailSendingException
            || exception instanceof EmailTemplateLoadException) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        } else if (exception instanceof InvalidPasswordException) {
            status = HttpStatus.BAD_REQUEST;
        } else if (exception instanceof UserNotFoundException) {
            status = HttpStatus.NOT_FOUND;
        }

        return ResponseEntity.status(status).body(
            ResponseDTO.res(status, exception.getMessage()));
    }


}

