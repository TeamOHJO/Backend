package com.example.yanolja.global.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

    //EmailConfirm
    EMAIL_SENDING_FAILURE(HttpStatus.INTERNAL_SERVER_ERROR, "이메일 전송에 실패했습니다."),
    EMAIL_TEMPLATE_LOAD_FAILURE(HttpStatus.INTERNAL_SERVER_ERROR, "이메일 템플릿 로드에 실패했습니다."),

    // USER
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 회원입니다."),
    USER_ALREADY_REGISTERED(HttpStatus.BAD_REQUEST, "이미 가입된 회원입니다."),
    INVALID_PHONENUMBER(HttpStatus.LENGTH_REQUIRED, "유효하지 않은 휴대폰 번호 형식"),
    INVALID_EMAIL(HttpStatus.PRECONDITION_FAILED, "유효하지 않은 이메일 형식"),
    INVALID_PASSWORD(HttpStatus.BAD_REQUEST, "잘못된 비밀번호입니다."),

    //Reservation
    RESERVATION_CONFLICT(HttpStatus.BAD_REQUEST, "예약하려는 시간대에 예약이 존재합니다."),
    INVALID_CANCEL_RESERVATION_REQUEST(HttpStatus.BAD_REQUEST, "예약이 존재하지 않거나 이미 취소되었습니다."),
    INVALID_RESERVATION_ID(HttpStatus.BAD_REQUEST, "존재하지 않는 reservationId입니다."),

    //Basket
    DUPLICATED_BASKET_CONTENT(HttpStatus.BAD_REQUEST, "이미 장바구니에 같은 상품이 있습니다."),

    //Review
    REVIEW_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 리뷰입니다."),
    INVALID_BASKET_ID(HttpStatus.BAD_REQUEST, "존재하지 않는 장바구니입니다."),

    //ACCOMODATIONLIKES
    INVALID_ARGUMENT(HttpStatus.BAD_REQUEST, "잘못된 사용자 ID 또는 숙소 ID입니다."),

    //Accomodation
    ACCOMMODATION_NOT_FOUND(HttpStatus.NOT_FOUND, "숙소를 찾을 수 없습니다."),

    //AccomodationRooms
    INVALID_ACCOMMODATION_ID(HttpStatus.NOT_FOUND, "존재하지 않는 방입니다."),

    //Permission
    PERMISSION_DENIED(HttpStatus.BAD_REQUEST, "해당 작업을 수행 할 권한이 존재하지 않습니다."),

    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 내부 에러"),

    //APIService
    JSON_PARSING_ERROR(HttpStatus.BAD_REQUEST, "JSON 파싱 오류가 발생했습니다."),
    API_CALL_FAILURE(HttpStatus.INTERNAL_SERVER_ERROR, "외부 API 호출에 실패했습니다."),
    API_PROCESSING_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "API 처리 중 오류가 발생했습니다.");


    private HttpStatus httpStatus;
    private String message;

    ErrorCode(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }
}
