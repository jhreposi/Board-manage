package com.example.board.api;
public class InvalidException extends RuntimeException{
    public InvalidException() {
    }

    public InvalidException(String message) {
        super(message);
    }
}
