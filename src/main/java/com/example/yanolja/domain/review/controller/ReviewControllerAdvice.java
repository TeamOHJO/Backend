package com.example.yanolja.domain.review.controller;

import com.example.yanolja.domain.review.error.ReviewNotFoundException;
import com.example.yanolja.domain.review.error.ReviewOperationException;
import com.example.yanolja.global.util.ResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class ReviewControllerAdvice {

    @ExceptionHandler(value = {
        ReviewNotFoundException.class
    })
    public ResponseEntity<ResponseDTO<Object>> handleReviewNotFoundException(
        ReviewNotFoundException exception
    ) {
        return ResponseEntity.status(exception.getErrorCode().getHttpStatus()).body(
            ResponseDTO.res(exception.getErrorCode().getHttpStatus(),
                exception.getMessage()));
    }

    @ExceptionHandler(value = {
        ReviewOperationException.class
    })
    public ResponseEntity<ResponseDTO<Object>> handleReviewOperationException(
        ReviewOperationException exception
    ) {
        return ResponseEntity.status(exception.getErrorCode().getHttpStatus()).body(
            ResponseDTO.res(exception.getErrorCode().getHttpStatus(),
                exception.getMessage()));
    }
}
