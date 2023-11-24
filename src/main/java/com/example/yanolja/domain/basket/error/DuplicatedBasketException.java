package com.example.yanolja.domain.basket.error;

import com.example.yanolja.global.exception.ApplicationException;
import com.example.yanolja.global.exception.ErrorCode;

public class DuplicatedBasketException extends ApplicationException {

    public DuplicatedBasketException(ErrorCode errorCode) {
        super(errorCode);
    }
}
