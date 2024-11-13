package com.example.board.dto.mappers;

import com.example.board.dto.CommentDto;
import com.example.board.model.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CommentMapper {
    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

    Comment toCommentFrom(CommentDto commentDto);

    List<CommentDto> toCommentDtoFrom(List<Comment> comments);
}
