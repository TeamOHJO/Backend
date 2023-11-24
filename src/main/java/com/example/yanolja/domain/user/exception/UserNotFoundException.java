package com.example.yanolja.domain.user.exception;


import com.example.yanolja.global.exception.ApplicationException;
import com.example.yanolja.global.exception.ErrorCode;

public class UserNotFoundException extends ApplicationException {

    public UserNotFoundException(String message) {
        super(ErrorCode.USER_NOT_FOUND, message);
    }
}
