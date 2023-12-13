package com.example.yanolja.domain.review.controller;

import com.example.yanolja.domain.review.exception.PermissionDeniedException;
import com.example.yanolja.domain.review.exception.ReviewNotFoundException;
import com.example.yanolja.global.util.ResponseDTO;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class ReviewControllerAdvice {

    @ExceptionHandler
    public ResponseEntity<ResponseDTO<Object>> badCredentialsException(BadCredentialsException e) {
        Map<String, Object> message = new HashMap<>();
        message.put("error", e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .contentType(MediaType.APPLICATION_JSON)
            .body(ResponseDTO.res(HttpStatus.BAD_REQUEST, message));
    }

    @ExceptionHandler
    public ResponseEntity<ResponseDTO> PermissionDeniedException(PermissionDeniedException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            ResponseDTO.res(HttpStatus.BAD_REQUEST,
                e.getMessage()));
    }

    @ExceptionHandler
    public ResponseEntity<ResponseDTO> ReviewNotFoundException(ReviewNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
            ResponseDTO.res(HttpStatus.NOT_FOUND,
                e.getMessage()));
    }

}
