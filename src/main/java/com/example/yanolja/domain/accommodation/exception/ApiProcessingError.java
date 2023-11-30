package com.example.yanolja.domain.accommodation.exception;

import com.example.yanolja.global.exception.ApplicationException;
import com.example.yanolja.global.exception.ErrorCode;

public class ApiProcessingError extends ApplicationException {

  public ApiProcessingError(ErrorCode errorCode) {

    super(errorCode);
  }

}
