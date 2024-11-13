package com.example.board.service;

import com.example.board.model.Comment;
import com.example.board.repository.CommentMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CommentService {
    CommentMapper commentMapper;

    public CommentService(CommentMapper commentMapper) {
        this.commentMapper = commentMapper;
    }

    public void createComment(Comment comment) {
        commentMapper.insertComment(comment);
    }

    public List<Comment> getCommentsBy(int articleId) {
        return commentMapper.selectComments(articleId);
    }
}
