package com.example.board;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@PropertySource("classpath:db.properties")
@SpringBootApplication
public class BoardAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(BoardAdminApplication.class, args);
    }

}
