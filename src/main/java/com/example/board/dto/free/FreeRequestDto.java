package com.example.board.dto.free;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class FreeRequestDto {
    private int articleId;

    @Min(value = 1, message = "유효한 카테고리가 아닙니다")
    private int categoryId;

    @NotBlank
    @Size(max = 100, message = "제목은 최대 100글자입니다")
    private String title;

    @NotBlank
    @Size(max = 4000, message = "본문은 최대 4000글자입니다")
    private String content;

    private int registerId;
}
