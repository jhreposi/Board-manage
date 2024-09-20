package com.example.board.service;

import com.example.board.global.exception.ContentNotFoundException;
import com.example.board.model.Article;
import com.example.board.model.Category;
import com.example.board.repository.ArticleMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleService {
    ArticleMapper articleMapper;

    public ArticleService(ArticleMapper articleMapper) {
        this.articleMapper = articleMapper;
    }

    public List<Article> getArticle() {
        return articleMapper.selectNoticeList();
    }

    public List<Category> getCategoriesBy(int boardType) {
        return articleMapper.selectCategoriesBy(boardType);
    }

    public int createArticle(Article article) {
        articleMapper.insertArticle(article);
        int generatedArticleId = article.getArticleId();

        return generatedArticleId;
    }

    public Article getArticleDetail(int articleId) {
        Optional<Article> article = articleMapper.selectArticleById(articleId);
        if (article.isEmpty()) {
            throw new ContentNotFoundException("Article not found");
        }
        return article.get();

    }

    public void modifyArticle(Article article) {
        articleMapper.updateArticle(article);
    }
}
