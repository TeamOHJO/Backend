package com.example.yanolja.domain.user.error;

public class InvalidPhonenumberException extends IllegalArgumentException {

    public InvalidPhonenumberException() {
        super("잘못된 휴대폰 번호 형식입니다.");
    }
}
