package com.example.board.repository;

import com.example.board.model.Article;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ArticleMapper {
    List<Article> selectNoticeList();
}
