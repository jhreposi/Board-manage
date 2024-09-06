package com.example.board.controller;

import com.example.board.dto.AdminReq;
import com.example.board.model.Admin;
import com.example.board.service.EncryptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import java.security.PublicKey;

@Slf4j
@Controller
public class LoginController {
    EncryptService encryptService;

    public LoginController(EncryptService encryptService) {
        this.encryptService = encryptService;
    }

    @GetMapping("/login")
    public String loginView(Model model) {
        PublicKey publicKey = encryptService.getPublicKey();
        String keyToPem = encryptService.publicKeyToPem(publicKey);

        //암호화를 위한 로그인시 서버 시작시 생성된 공개키
        model.addAttribute("publicKey", keyToPem);

        return "view/login/login";
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginAction(AdminReq.LoginDto loginDto) throws Exception {
        log.info("info => {}", loginDto);
        String decrypted = encryptService.decrypt(loginDto.getPassword());
        loginDto.setPassword(decrypted);

        // TODO: 2024-09-06 modelMapper로 변환 못하고있음
        Admin admin = Admin.from(loginDto);
        log.info("admin info => {}", admin);
        return ResponseEntity.ok(null);
    }

}
