package com.example.board.model;

import com.example.board.dto.ArticleReqDto;
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
    private Admin admin;

    public static Article from(ArticleReqDto.NoticePost dto) {
        Article article = Article.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .pinnedYn(dto.getPinnedYn())
                .admin(Admin.builder().adminId(dto.getAdminId()).build())
                .category(Category.builder().categoryId(dto.getCategoryId()).build())
                .build();
        return article;
    }


}
