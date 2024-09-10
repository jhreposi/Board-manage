package com.example.board.api;

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
