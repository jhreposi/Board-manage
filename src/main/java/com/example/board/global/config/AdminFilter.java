package com.example.board.global.config;

import com.example.board.dto.AdminRes;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

public class AdminFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        HttpSession session = httpRequest.getSession(false);
        AdminRes.InfoDto admin = null;

        if (session != null) {
            admin = (AdminRes.InfoDto) session.getAttribute("adminInfo");
        }
        if (admin == null || !"ADMIN".equals(admin.getRole())) {
            httpResponse.sendRedirect("/login?error");
        }

        chain.doFilter(request, response);
    }
}
