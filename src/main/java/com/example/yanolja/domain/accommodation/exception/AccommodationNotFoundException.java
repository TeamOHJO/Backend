package com.example.yanolja.domain.accommodation.exception;

import com.example.yanolja.global.exception.ApplicationException;
import com.example.yanolja.global.exception.ErrorCode;

public class AccommodationNotFoundException extends ApplicationException {
  public AccommodationNotFoundException() {
    super(ErrorCode.ACCOMMODATION_NOT_FOUND);
  }
}