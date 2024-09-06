package com.example.board.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class AdminReq {

    @Getter
    @Setter
    @ToString
    public static class LoginDto {
        private String loginId;
        private String password;

    }
}
