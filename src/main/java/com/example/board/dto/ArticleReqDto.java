package com.example.board.dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class ArticleReqDto {

    public enum BoardName {
        // 1:NOTICE, 2:FREE, 3:GALLERY, 4:QUESTION
        NOTICE(1),
        FREE(2),
        GALLERY(3),
        QUESTION(4)
        ;
        private int boardType;
        BoardName(int boardType) {
            this.boardType = boardType;
        }
        String getBoardNameFrom(int boardType) {
            for (BoardName value : values()) {
                if (value.equals(boardType)) {
                    return value.name();
                }
            }
            throw new RuntimeException("일치하는 게시판 없음");
        }
    }

    @Getter
    @Setter
    @ToString
    public class Notice {
        private String title;
        private String content;
        private String pinnedYn;
        private int categoryId;

    }
}
