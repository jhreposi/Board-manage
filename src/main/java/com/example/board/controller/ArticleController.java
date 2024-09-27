package com.example.board.controller;

import com.example.board.dto.CategoryDto;
import com.example.board.model.Category;
import com.example.board.service.ArticleService;
import java.util.List;

public abstract class ArticleController {
    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    protected List<CategoryDto> getCategories(int boardType) {
        List<Category> categoriesVo = articleService.getCategoriesBy(boardType);

        //todo mapping
        return null;
    }
}
