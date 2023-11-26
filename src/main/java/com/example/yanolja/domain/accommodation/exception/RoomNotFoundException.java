package com.example.yanolja.domain.accommodation.exception;

import com.example.yanolja.global.exception.ApplicationException;
import com.example.yanolja.global.exception.ErrorCode;

public class RoomNotFoundException extends ApplicationException {
    public RoomNotFoundException(ErrorCode errorCode) {
      super(errorCode);
    }
}
