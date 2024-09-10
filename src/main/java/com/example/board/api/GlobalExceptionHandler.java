package com.example.board.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidException.class)
    public ResponseEntity<ErrorResponse> invalidExceptionHandler(InvalidException e) {
        log.info("invalidException message => {}", e.getMessage());

        ErrorResponse errorResponse = ErrorResponse.builder()
                .code(ErrorResponseApi.INVALIDED_INFO.getCode())
                .message(ErrorResponseApi.INVALIDED_INFO.getMessage())
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> runtimeExceptionHandler(RuntimeException e) {
        log.info("RuntimeException message => {}", e.getMessage());

        ErrorResponse errorResponse = ErrorResponse.builder()
                .code("E000")
                .message(e.getMessage())
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

}
