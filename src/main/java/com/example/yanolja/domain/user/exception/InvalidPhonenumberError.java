package com.example.yanolja.domain.user.exception;

import com.example.yanolja.global.exception.ApplicationException;
import com.example.yanolja.global.exception.ErrorCode;

public class InvalidPhonenumberError extends ApplicationException {

    public InvalidPhonenumberError(ErrorCode errorCode) {
        super(errorCode);
    }
}
