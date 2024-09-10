package com.example.board.controller;

import com.example.board.dto.AdminReq;
import com.example.board.dto.AdminRes;
import com.example.board.api.ResponseData;
import com.example.board.model.Admin;
import com.example.board.service.EncryptService;
import com.example.board.service.LoginService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
    EncryptService encryptService;
    LoginService loginService;

    public LoginController(EncryptService encryptService, LoginService loginService) {
        this.encryptService = encryptService;
        this.loginService = loginService;
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
    public ResponseEntity<ResponseData> loginAction(@RequestBody AdminReq.LoginDto loginDto, HttpServletRequest request, HttpServletResponse response) throws Exception {
        //암호화된 비밀번호 복호화
        String decrypted = encryptService.decrypt(loginDto.getEncryptedPassword());
        loginDto.setPassword(decrypted);

        Admin requestAdmin = Admin.from(loginDto);

        int adminId = loginService.getIdByAdmin(requestAdmin);
        Admin responseAdmin = loginService.getAdminById(adminId);

        HttpSession session = request.getSession(true);

        if (session.isNew()) {
            session.setAttribute("adminId", responseAdmin.getAdminId());
            session.setAttribute("loginId", responseAdmin.getLoginId());
            session.setAttribute("role", responseAdmin.getRole());
            session.setMaxInactiveInterval(60 * 30); //만료시간 30분

        } else {
            // TODO: 2024-09-10 로그인상태에서 로그인 시도하면 접근제한, 기존세션제거?
        }
        return ResponseEntity.ok(ResponseData.builder().message("로그인 성공").build());
    }

}
