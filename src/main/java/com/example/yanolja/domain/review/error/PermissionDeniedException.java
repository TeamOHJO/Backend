package com.example.yanolja.domain.review.error;

import com.example.yanolja.global.exception.ApplicationException;
import com.example.yanolja.global.exception.ErrorCode;

public class PermissionDeniedException extends ApplicationException {

    public PermissionDeniedException(ErrorCode errorCode) {
        super(errorCode);
    }
}
