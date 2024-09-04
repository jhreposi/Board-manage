package com.example.board.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ArticleInfo {
    private Article article;
    private Board board;
    private Category category;



}
