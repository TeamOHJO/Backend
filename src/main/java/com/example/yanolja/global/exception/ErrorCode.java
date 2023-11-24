package com.example.yanolja.global.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

    // USER
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 회원입니다."),
    USER_ALREADY_REGISTERED(HttpStatus.BAD_REQUEST, "이미 가입된 회원입니다."),
    INVALID_PHONENUMBER(HttpStatus.LENGTH_REQUIRED, "유효하지 않은 휴대폰 번호 형식"),
    INVALID_EMAIL(HttpStatus.PRECONDITION_FAILED, "유효하지 않은 이메일 형식"),

    //Reservation
    INVALID_ACCOMMODATION_ID(HttpStatus.BAD_REQUEST, "존재하지 않는 방입니다."),
    //Basket
    DUPLICATED_BASKET_CONTENT(HttpStatus.BAD_REQUEST, "이미 장바구니에 같은 상품이 있습니다."),
    //Review
    REVIEW_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 리뷰입니다."),


    //ACCOMODATIONLIKES
    INVALID_ARGUMENT(HttpStatus.BAD_REQUEST, "잘못된 사용자 ID 또는 숙소 ID입니다."),

    // 5xx
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 내부 에러"),
    ACCOMMODATION_NOT_FOUND(HttpStatus.NOT_FOUND, "숙소를 찾을 수 없습니다.");

    private HttpStatus httpStatus;
    private String message;

    ErrorCode(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }
}
