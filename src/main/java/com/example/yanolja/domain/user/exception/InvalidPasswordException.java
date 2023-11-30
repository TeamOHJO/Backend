package com.example.yanolja.domain.user.exception;

import com.example.yanolja.global.exception.ApplicationException;
import com.example.yanolja.global.exception.ErrorCode;

public class InvalidPasswordException extends ApplicationException {

    public InvalidPasswordException(ErrorCode errorCode) {

        super(errorCode);
    }

}
