package com.example.board.global.exception;

import com.example.board.global.response.ErrorResponse;
import com.example.board.global.response.ErrorResponseApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
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

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> runtimeExceptionHandler(Exception e) {
        log.info("Exception message => {}", e.getMessage());

        ErrorResponse errorResponse = ErrorResponse.builder()
                .code("E000")
                .message(e.getMessage())
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> methodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.info("MethodArgumentNotValidException => {}", e.getMessage());

        ErrorResponse errorResponse = ErrorResponse.builder()
                .code(ErrorResponseApi.ARGUMENT_NOT_VALID.getCode())
                .message(ErrorResponseApi.ARGUMENT_NOT_VALID.getMessage())
                .build();
        return ResponseEntity.badRequest().body(errorResponse);
    }

}
