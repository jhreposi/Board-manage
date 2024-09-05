package com.example.board.model;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Category {

    private int categoryId;
    private String name;
    private int boardType;  // 1: NOTICE, 2: FREE, 3: GALLERY, 4: QUESTION

}
