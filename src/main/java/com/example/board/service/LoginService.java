package com.example.board.service;

import com.example.board.api.InvalidException;
import com.example.board.api.ErrorResponseApi;
import com.example.board.model.Admin;
import com.example.board.repository.LoginMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Slf4j
@Service
public class LoginService {
    LoginMapper loginMapper;

    public LoginService(LoginMapper loginMapper) {
        this.loginMapper = loginMapper;
    }

    public int getIdByAdmin(Admin admin) {
        Optional<Integer> adminId = loginMapper.selectValidAdmin(admin);
        if (adminId.isEmpty()) {
            throw new InvalidException(ErrorResponseApi.INVALIDED_INFO.getMessage());
        }
        return adminId.get();
    }

    public Admin getAdminById(int adminId) {
        return loginMapper.selectAdminById(adminId);
    }
}
