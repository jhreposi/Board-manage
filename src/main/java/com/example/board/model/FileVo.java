package com.example.board.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FileVo {

    private int fileId;
    private int articleId;
    private String filename;
    private String originalName;
    private String filePath;
    private Long fileSize;
    private String extension;
}
