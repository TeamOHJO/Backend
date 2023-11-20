package com.example.yanolja.global.util;

public record ResponseDto<T>(Integer code, String msg, T data) {

}