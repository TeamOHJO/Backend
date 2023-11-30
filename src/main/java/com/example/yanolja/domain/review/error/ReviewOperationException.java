package com.example.yanolja.domain.review.error;

import com.example.yanolja.global.exception.ApplicationException;
import com.example.yanolja.global.exception.ErrorCode;

public class ReviewOperationException extends ApplicationException {

    public ReviewOperationException(ErrorCode errorCode) {
        super(errorCode);
    }
}
