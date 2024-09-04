package com.example.board.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Board {

    private int boardId;
    private BoardType boardName;

    public enum BoardType {
        NOTICE,
        FREE,
        GALLERY,
        QUESTION,
        ;
    }
}
