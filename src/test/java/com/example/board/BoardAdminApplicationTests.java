package com.example.board;

import com.example.board.repository.ArticleMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BoardAdminApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    public void mapping(ArticleMapper mapper) {
        mapper.selectNoticeList().stream().forEach(article -> {
            System.out.println(article.toString());
        });
    }

}
