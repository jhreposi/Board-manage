package com.example.board.model;

import com.example.board.dto.AdminReq;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Admin {

    private String adminId;
    private String loginId;
    private String password;


    public static Admin from(AdminReq.LoginDto loginDto) {
        return Admin.builder()
                .loginId(loginDto.getLoginId())
                .password(loginDto.getPassword())
                .build();
    }

}
