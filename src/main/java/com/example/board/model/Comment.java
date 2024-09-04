package com.example.board.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
