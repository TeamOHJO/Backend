package com.example.yanolja.global.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

    // MEMBER
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 회원입니다."),
    USER_ALREADY_REGISTERED(HttpStatus.BAD_REQUEST, "이미 가입된 회원입니다."),
    DUPLICATE_NICKNAME(HttpStatus.BAD_REQUEST, "이미 사용 중인 닉네임 입니다."),
    ACCOUNT_RECOVERY_SUCCESSFUL(HttpStatus.OK, "계정이 성공적으로 복구되었습니다!"),
    REGISTRATION_SUCCESS(HttpStatus.OK, "가입 성공!"),
    REGISTRATION_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "회원가입 실패"),
    MEMBER_UPDATE_SUCCESS(HttpStatus.OK, "회원 정보가 업데이트되었습니다."),
    MY_PAGE_RETRIEVED_SUCCESS(HttpStatus.OK, "사용자 정보를 성공적으로 조회하였습니다."),


    // 5xx
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 내부 에러");

    private HttpStatus httpStatus;
    private String message;

    ErrorCode(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }
}
