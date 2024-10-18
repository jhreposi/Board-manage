package com.example.board.repository;

import com.example.board.model.FileVo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FileMapper {

    void insertFile(FileVo fileVo);
}
