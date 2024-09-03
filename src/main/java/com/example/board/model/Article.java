package com.example.board.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Article {

    private int articleId;
    private int categoryId;
    private int memberId;
    private ArticleType articleType;
    private String title;
    private String content;
    private int viewCount;
    private String createdAt;
    private String updatedAt;

    @Getter
    public enum ArticleType {
        NOTICE,
        IMAGE,
        ;
    }
}
