package com.example.yanolja.domain.user.exception;

import com.example.yanolja.global.exception.ApplicationException;
import com.example.yanolja.global.exception.ErrorCode;

public class InvalidPhonenumberException extends ApplicationException {

    private static final ErrorCode ERROR_CODE = ErrorCode.INVALID_PHONENUMBER;


    public InvalidPhonenumberException() {
        super(ERROR_CODE);
    }
}
