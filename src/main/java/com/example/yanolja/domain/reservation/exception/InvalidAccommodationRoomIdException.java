package com.example.yanolja.domain.reservation.exception;

import com.example.yanolja.global.exception.ApplicationException;
import com.example.yanolja.global.exception.ErrorCode;

public class InvalidAccommodationRoomIdException extends ApplicationException {

    public InvalidAccommodationRoomIdException(ErrorCode errorCode) {
        super(errorCode);
    }
}
