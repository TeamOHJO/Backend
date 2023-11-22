package com.example.yanolja.domain.user.exception;

import com.example.yanolja.global.exception.ApplicationException;
import com.example.yanolja.global.exception.ErrorCode;

public class EmailDuplicateError extends ApplicationException {

    public EmailDuplicateError(ErrorCode errorCode) {

        super(errorCode);
    }
}
