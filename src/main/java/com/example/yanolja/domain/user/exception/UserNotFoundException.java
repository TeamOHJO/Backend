package com.example.yanolja.domain.user.exception;


import com.example.yanolja.global.exception.ApplicationException;
import com.example.yanolja.global.exception.ErrorCode;

public class UserNotFoundException extends ApplicationException {

    private static final ErrorCode ERROR_CODE = ErrorCode.USER_NOT_FOUND;


    public UserNotFoundException() {
        super(ERROR_CODE);
    }
}
