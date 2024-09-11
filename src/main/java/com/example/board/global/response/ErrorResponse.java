package com.example.board.global.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class ErrorResponse {
    private boolean result;
    private String code;
    private String message;

    public ErrorResponse() {
        this.result = false;
    }
}
