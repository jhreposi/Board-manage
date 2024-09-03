package com.example.board.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    private int commentId;
    private int articleId;
    private int memberId;
    private String content;
    private String createdAt;
}
