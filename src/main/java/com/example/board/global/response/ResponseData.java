package com.example.board.global.response;


import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ResponseData<T> {
    private T data;
    private String message;
}
