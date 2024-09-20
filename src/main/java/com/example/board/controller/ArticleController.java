package com.example.board.controller;

import com.example.board.dto.CategoryDto;
import com.example.board.model.Category;
import com.example.board.service.ArticleService;
import org.modelmapper.ModelMapper;
import java.util.List;

public abstract class ArticleController {
    private final ArticleService articleService;
    private final ModelMapper modelMapper;

    public ArticleController(ArticleService articleService, ModelMapper modelMapper) {
        this.articleService = articleService;
        this.modelMapper = modelMapper;
    }

    protected List<CategoryDto> getCategories(int boardType) {
        List<Category> categoriesVo = articleService.getCategoriesBy(boardType);

        List<CategoryDto> categories = categoriesVo.stream()
                .map(category -> modelMapper
                        .map(category, CategoryDto.class))
                .toList();
        return categories;
    }
}
