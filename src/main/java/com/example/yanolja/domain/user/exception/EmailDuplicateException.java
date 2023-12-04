package com.example.yanolja.domain.user.exception;

import com.example.yanolja.global.exception.ApplicationException;
import com.example.yanolja.global.exception.ErrorCode;

public class EmailDuplicateException extends ApplicationException {

    private static final ErrorCode ERROR_CODE = ErrorCode.USER_ALREADY_REGISTERED;

    public EmailDuplicateException() {

        super(ERROR_CODE);
    }
}
