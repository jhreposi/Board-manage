package com.example.board.global.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<AdminFilter> adminFilter() {
        FilterRegistrationBean<AdminFilter> adminFilterBean = new FilterRegistrationBean<>();
        adminFilterBean.setFilter(new AdminFilter());
        adminFilterBean.addUrlPatterns("/admin/*");

        return adminFilterBean;
    }
}