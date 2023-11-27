package com.example.yanolja.domain.reservation.exception;

import com.example.yanolja.global.exception.ApplicationException;
import com.example.yanolja.global.exception.ErrorCode;

public class InvalidReservationException extends ApplicationException {

    public InvalidReservationException(ErrorCode errorCode) {
        super(errorCode);
    }
}
