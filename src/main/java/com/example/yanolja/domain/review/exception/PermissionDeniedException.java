package com.example.yanolja.domain.review.exception;

import com.example.yanolja.global.exception.ApplicationException;
import com.example.yanolja.global.exception.ErrorCode;

public class PermissionDeniedException extends ApplicationException {

    private static final ErrorCode ERROR_CODE = ErrorCode.PERMISSION_DENIED;

    public PermissionDeniedException() {

        super(ERROR_CODE);
    }
}
