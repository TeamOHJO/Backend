package com.example.yanolja.domain.accommodation.exception;

import com.example.yanolja.global.exception.ApplicationException;
import com.example.yanolja.global.exception.ErrorCode;

public class JsonParsingError extends ApplicationException {

  public JsonParsingError(ErrorCode errorCode) {
    super(errorCode);
  }
}