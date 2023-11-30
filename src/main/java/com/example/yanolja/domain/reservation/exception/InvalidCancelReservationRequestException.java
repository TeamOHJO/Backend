package com.example.yanolja.domain.reservation.exception;

import com.example.yanolja.global.exception.ApplicationException;
import com.example.yanolja.global.exception.ErrorCode;

public class InvalidCancelReservationRequestException extends ApplicationException {

    public InvalidCancelReservationRequestException(ErrorCode errorCode) {
        super(errorCode);
    }
}
