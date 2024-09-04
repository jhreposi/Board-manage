package com.example.board.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Article {

    private int articleId;
    private String title;
    private String content;
    private int viewCount;
    private String SecretYn;
    private String PinnedYn;
    private String createdAt;
    private String updatedAt;
    private Board board;
    private Category category;


}
