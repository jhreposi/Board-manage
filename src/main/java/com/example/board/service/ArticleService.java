package com.example.board.service;

import com.example.board.model.Article;
import com.example.board.repository.ArticleMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {
    ArticleMapper articleMapper;

    public ArticleService(ArticleMapper articleMapper) {
        this.articleMapper = articleMapper;
    }

    public List<Article> getArticle() {
        return articleMapper.selectNoticeList();
    }

}
