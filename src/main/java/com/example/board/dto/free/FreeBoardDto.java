package com.example.board.dto.free;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class FreeBoardDto {
    int articleId;
    String categoryName;
    String title;
    int viewCount;
    String createdAt;
    String register;
}
