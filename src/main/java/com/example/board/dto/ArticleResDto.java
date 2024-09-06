package com.example.board.dto;

import lombok.*;

public final class ArticleResDto {
    public ArticleResDto() {
    }
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    public static class NoticeList {
        int articleId;
        String categoryName;
        String title;
        int viewCount;
        String createdAt;
        String register;
    }
}
