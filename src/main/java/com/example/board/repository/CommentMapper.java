package com.example.board.repository;

import com.example.board.model.Comment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {

    void insertComment(Comment comment);

    List<Comment> selectComments(int articleId);
}
