package com.example.yanolja.domain.user.exception;

import com.example.yanolja.global.exception.ApplicationException;
import com.example.yanolja.global.exception.ErrorCode;

public class EmailSendingException extends ApplicationException {

    private static final ErrorCode ERROR_CODE = ErrorCode.EMAIL_SENDING_FAILURE;

    public EmailSendingException() {

        super(ERROR_CODE);
    }

}
