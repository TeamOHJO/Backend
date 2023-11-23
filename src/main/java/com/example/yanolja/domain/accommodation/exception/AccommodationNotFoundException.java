package com.example.yanolja.domain.accommodation.exception;

public class AccommodationNotFoundException extends ApplicationException{
  public AccommodationNotFoundException(ErrorCode errorCode) {
    super(errorCode);
  }
}
