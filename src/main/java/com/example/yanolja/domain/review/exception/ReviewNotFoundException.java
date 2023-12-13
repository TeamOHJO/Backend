package com.example.yanolja.domain.review.exception;

import com.example.yanolja.global.exception.ApplicationException;
import com.example.yanolja.global.exception.ErrorCode;

public class ReviewNotFoundException extends ApplicationException {

    private static final ErrorCode ERROR_CODE = ErrorCode.REVIEW_NOT_FOUND;

    public ReviewNotFoundException() {

        super(ERROR_CODE);
    }
}
