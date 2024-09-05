package com.example.board.model;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Article {

    private int articleId;
    private String title;
    private String content;
    private int viewCount;
    private String createdAt;
    private String updatedAt;

    private String secretYn;
    private String pinnedYn;

    @ToString.Include
    private Category category;


}
