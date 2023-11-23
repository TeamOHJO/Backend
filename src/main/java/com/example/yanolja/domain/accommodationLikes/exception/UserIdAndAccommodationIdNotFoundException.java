package com.example.yanolja.domain.accommodationLikes.exception;


import com.example.yanolja.global.exception.ApplicationException;
import com.example.yanolja.global.exception.ErrorCode;

public class UserIdAndAccommodationIdNotFoundException extends ApplicationException {

    public UserIdAndAccommodationIdNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}
