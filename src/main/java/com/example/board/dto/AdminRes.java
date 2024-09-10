package com.example.board.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

public class AdminRes {

    @Getter
    @Setter
    @NoArgsConstructor
    @ToString
    public static class AdminInfoDto {
        private int adminId;
        private String loginId;
        private String role;
    }
}
