package com.example.yanolja.global.springsecurity;

public class InvalidPrincipalDetailsException extends RuntimeException {

    public InvalidPrincipalDetailsException() {
        super("유저를 인증할 수 없습니다.");
    }
}
