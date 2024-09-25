package com.example.board.model;

import lombok.Getter;

@Getter
public enum Board {
    // 1:NOTICE, 2:FREE, 3:GALLERY, 4:QUESTION

    NOTICE(1),
    FREE(2),
    GALLERY(3),
    QUESTION(4)
    ;
    private final int boardType;

    Board(int boardType) {
        this.boardType = boardType;
    }

    public static int getBoardTypeFrom(String boardName) {
        for (Board board : values()) {
            if (board.name().equalsIgnoreCase(boardName)) {
                return board.getBoardType();
            }
        }
        throw new RuntimeException("일치 하는 게시판 없음");
    }
}
