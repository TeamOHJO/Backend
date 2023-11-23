package com.example.yanolja.domain.accommodation.exception;

public class RoomNotFoundException extends ApplicationException {
    public RoomNotFoundException(ErrorCode errorCode) {
      super(errorCode);
    }
}
