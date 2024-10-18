package com.example.board.dto.mappers;

import com.example.board.dto.FileResDto;
import com.example.board.model.FileVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface FileMapper {
    FileMapper INSTANCE = Mappers.getMapper(FileMapper.class);

    List<FileResDto> toFileDtoFrom(List<FileVo> files);
}
