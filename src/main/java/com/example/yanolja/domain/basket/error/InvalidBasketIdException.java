package com.example.yanolja.domain.basket.error;

import com.example.yanolja.global.exception.ApplicationException;
import com.example.yanolja.global.exception.ErrorCode;

public class InvalidBasketIdException extends ApplicationException {

    public InvalidBasketIdException(ErrorCode errorCode) {
        super(errorCode);
    }
}
