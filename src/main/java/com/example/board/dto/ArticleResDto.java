package com.example.board.dto;

import lombok.*;

public class ArticleResDto {
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

    @Getter
    @Setter
    @ToString
    @NoArgsConstructor
    public static class Category {
        private int categoryId;
        private String name;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @ToString
    public static class ArticleDetail {
        private int articleId;
        private int categoryId;
        private String title;
        private String content;
        private int viewCount;
        private String pinnedYn;
        private String createdAt;
        private String updatedAt;

        private String categoryName;
        private String adminName;
    }
}
