package com.example.board.repository;

import com.example.board.model.FileVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface FileMapper {

    void insertFile(FileVo fileVo);

    List<FileVo> selectFilesByArticleId(int articleId);

    Optional<FileVo> selectFileById(int fileId);
}
