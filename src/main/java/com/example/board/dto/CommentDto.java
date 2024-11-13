package com.example.board.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class CommentDto {
    private int commentId;
    private int articleId;
    private int memberId;
    private String content;
    private String createdAt;
}
