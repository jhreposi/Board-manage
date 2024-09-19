package com.example.board.global.response;


import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ResponseData<T> {
    private boolean result;
    private T data;
    private String message;
}
