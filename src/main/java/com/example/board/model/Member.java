package com.example.board.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Member {

    private int memberId;
    private String loginId;
    private String password;
    private String username;
}
