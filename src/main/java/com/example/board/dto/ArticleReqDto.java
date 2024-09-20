package com.example.board.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class ArticleReqDto {

    @Getter
    @Setter
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class NoticePost {
        private int articleId;

        @Min(value = 1, message = "유효한 카테고리가 아닙니다")
        private int categoryId;

        @NotBlank
        @Size(max = 100, message = "제목은 최대 100글자입니다")
        private String title;

        @NotBlank
        @Size(max = 4000, message = "본문은 최대 4000글자입니다")
        private String content;

        @Builder.Default
        private String pinnedYn = "N";

        private int adminId;

    }
}
