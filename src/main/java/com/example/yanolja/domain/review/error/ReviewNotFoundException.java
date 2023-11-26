package com.example.yanolja.domain.review.error;

import com.example.yanolja.global.exception.ApplicationException;
import com.example.yanolja.global.exception.ErrorCode;

public class ReviewNotFoundException extends ApplicationException {

    public ReviewNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}
