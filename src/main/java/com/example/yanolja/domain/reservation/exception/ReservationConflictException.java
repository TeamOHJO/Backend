package com.example.yanolja.domain.reservation.exception;

import com.example.yanolja.global.exception.ApplicationException;
import com.example.yanolja.global.exception.ErrorCode;

public class ReservationConflictException extends ApplicationException {

    public ReservationConflictException(ErrorCode errorCode) {
        super(errorCode);
    }
}
