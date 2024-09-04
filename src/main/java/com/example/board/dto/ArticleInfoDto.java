package com.example.board.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArticleInfoDto {
    private int articleId;
    private int boardId;
    private int memberId;
    private String title;
    private String content;
    private int viewCount;
    private String SecretYn;
    private String PinnedYn;
    private String createdAt;
    private String updatedAt;
    private String boardName;
    private String categoryName;

}
