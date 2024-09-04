package com.example.board.controller;

import com.example.board.dto.ArticleInfoDto;
import com.example.board.model.Article;
import com.example.board.service.ArticleService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
@Slf4j
@RequestMapping("/")
public class ArticleController {
    ArticleService articleService;

    @GetMapping("/notice")
    public String getArticle(Model model) {
        List<Article> article = articleService.getArticle();

        ModelMapper modelMapper = new ModelMapper();
        List<ArticleInfoDto> articleInfoDto = article.stream().map(param -> modelMapper.map(param, ArticleInfoDto.class)).collect(Collectors.toList());

        model.addAttribute("articles", articleInfoDto);
        return "notice";
    }
}
