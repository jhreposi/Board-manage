package com.example.board.repository;

import com.example.board.model.Article;
import com.example.board.model.Category;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ArticleMapper {
    List<Article> selectNoticeList();

    List<Category> selectCategoriesBy(int boardType);
}
