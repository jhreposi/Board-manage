package com.example.board.global.response;

import lombok.Getter;

@Getter
public enum ErrorResponseApi {
    INVALIDED_INFO("E001", "유효한 정보가 아닙니다"),
    ARGUMENT_NOT_VALID("E002", "유효성 검증에 실패하였습니다")
    ;
    private String code;
    private String message;

    ErrorResponseApi(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
