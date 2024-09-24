package com.example.board.repository;

import com.example.board.dto.SearchRequest;
import com.example.board.model.Article;
import com.example.board.model.Category;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ArticleMapper {
    List<Article> selectArticleList(SearchRequest searchRequest);

    List<Category> selectCategoriesBy(int boardType);

    void insertArticle(Article article);

    Optional<Article> selectArticleById(int articleId);

    void updateArticle(Article article);

    void deleteArticleBy(int articleId);
}
