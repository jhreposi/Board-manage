package com.example.board.api;

import lombok.Getter;

@Getter
public enum ErrorResponseApi {
    INVALIDED_INFO("E001", "유효한 정보가 아닙니다")
    ;
    private String code;
    private String message;

    ErrorResponseApi(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
