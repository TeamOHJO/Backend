package com.example.yanolja.domain.accommodation.exception;

import com.example.yanolja.global.exception.ApplicationException;
import com.example.yanolja.global.exception.ErrorCode;

public class ApiCallError extends ApplicationException {
  public ApiCallError(ErrorCode errorCode){
    super(errorCode);
  }
}
