package com.calmdown.simpleGw.exception.errorCode;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CommonErrorCode implements ErrorCode{

    INVALID_PARAMETER(HttpStatus.BAD_REQUEST, "Invalid parameter included"),
    RESOURCE_NOT_FOUND(HttpStatus.NOT_FOUND, "Resource not exists"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error"),
    
    /*
     * 커스텀 에러코드
     */
    INVALID_PAYMENT_ID(HttpStatus.BAD_REQUEST, "잘못된 통신사 거래번호입니다.")
    ;

    private final HttpStatus httpStatus;
    private final String message;

}
