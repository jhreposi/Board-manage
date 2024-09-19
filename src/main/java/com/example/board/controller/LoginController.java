package com.example.board.controller;

import com.example.board.dto.AdminReq;
import com.example.board.global.response.ResponseData;
import com.example.board.dto.AdminRes;
import com.example.board.model.Admin;
import com.example.board.service.EncryptService;
import com.example.board.service.LoginService;
import com.example.board.service.SessionHelper;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.PublicKey;

@Slf4j
@Controller
public class LoginController {
    SessionHelper sessionHelper;
    EncryptService encryptService;
    LoginService loginService;
    ModelMapper modelMapper;

    public LoginController(SessionHelper sessionHelper,EncryptService encryptService, LoginService loginService, ModelMapper modelMapper) {
        this.sessionHelper = sessionHelper;
        this.encryptService = encryptService;
        this.loginService = loginService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/login")
    public String loginView(Model model) {
        PublicKey publicKey = encryptService.getPublicKey();
        String keyToPem = encryptService.publicKeyToPem(publicKey);

        //암호화를 위한 로그인시 서버 시작시 생성된 공개키
        model.addAttribute("publicKey", keyToPem);

        return "view/login/login";
    }

    @ResponseBody
    @PostMapping("/login")
    public ResponseEntity<ResponseData<Object>> loginAction(@RequestBody AdminReq.LoginDto loginDto) throws Exception {
        //암호화된 비밀번호 복호화
        String decrypted = encryptService.decrypt(loginDto.getEncryptedPassword());
        loginDto.setPassword(decrypted);

        Admin requestAdmin = Admin.from(loginDto);

        int adminId = loginService.getAdminIdByLoginInfo(requestAdmin);
        Admin responseAdmin = loginService.getAdminById(adminId);
        AdminRes.InfoDto adminInfo = modelMapper.map(responseAdmin, AdminRes.InfoDto.class);

        if (sessionHelper.getAdminInfo() != null) {
            sessionHelper.removeAdminInfo();
        }
        sessionHelper.setAdminInfo(responseAdmin);

        ResponseData<Object> response = ResponseData.builder()
                .result(true)
                .data(adminInfo)
                .message("로그인 성공")
                .build();

        return ResponseEntity.ok(response);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout() {
        sessionHelper.removeAdminInfo();

        return ResponseEntity.ok("로그아웃 되었습니다");
    }

}
