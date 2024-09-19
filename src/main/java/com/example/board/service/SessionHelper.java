package com.example.board.service;

import com.example.board.dto.AdminRes;
import com.example.board.model.Admin;
import jakarta.servlet.http.HttpSession;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SessionHelper {
    private final HttpSession httpSession;

    public SessionHelper(HttpSession httpSession) {
        this.httpSession = httpSession;
    }

    public void setAdminInfo(Admin adminInfo) {
        httpSession.setAttribute("adminInfo", adminInfo);
        httpSession.setMaxInactiveInterval(60 * 30); //만료시간 30분
    }

    public Admin getAdminInfo() {
        return (Admin) httpSession.getAttribute("adminInfo");
    }

    public void removeAdminInfo() {
        httpSession.removeAttribute("adminInfo");
        httpSession.invalidate();
    }
}
