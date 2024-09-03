package com.example.board.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FileVo {

    private int fileId;
    private int articleId;
    private String filename;
    private String originalName;
    private String filePath;
    private String fileSize;
    private String extension;
}
