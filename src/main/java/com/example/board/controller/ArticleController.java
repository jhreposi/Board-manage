package com.example.board.controller;

import com.example.board.dto.ArticleResDto;
import com.example.board.dto.CategoryDto;
import com.example.board.dto.mappers.ArticleMapper;
import com.example.board.dto.mappers.CategoryMapper;
import com.example.board.model.Article;
import com.example.board.model.Category;
import com.example.board.service.ArticleService;
import java.util.List;

public abstract class ArticleController {
    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    protected List<CategoryDto> getCategories(int boardType) {
        List<Category> categories = articleService.getCategoriesBy(boardType);

        List<CategoryDto> categoriesDto = CategoryMapper.INSTANCE.toCategoryDtoList(categories);

        return categoriesDto;
    }

    protected ArticleResDto.ArticleDetail getArticleDetail(Integer articleId) {
        if (articleId != null) {
            Article article = articleService.getArticleDetail(articleId);
            ArticleResDto.ArticleDetail articleDetail = ArticleMapper.INSTANCE.toArticleDetailDto(article);

            return articleDetail;
        }
        return null;
    }
}
