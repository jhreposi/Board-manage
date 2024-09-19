package com.example.board.global.exception;

public class ContentNotFoundException extends RuntimeException {
    public ContentNotFoundException() {
    }

    public ContentNotFoundException(String message) {
        super(message);
    }
}
