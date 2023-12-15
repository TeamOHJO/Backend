package com.example.yanolja.domain.user.exception;

import com.example.yanolja.global.exception.ApplicationException;
import com.example.yanolja.global.exception.ErrorCode;

public class InvalidEmailException extends ApplicationException {

    public InvalidEmailException(ErrorCode errorCode) {
        super(errorCode);
    }
}
