package com.example.board.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Member {

    private int memberId;
    private String loginId;
    private String password;
    private String username;
}
