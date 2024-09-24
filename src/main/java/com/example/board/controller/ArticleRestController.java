package com.example.board.controller;

import com.example.board.dto.SearchRequest;
import com.example.board.global.response.ResponseData;
import com.example.board.model.Article;
import com.example.board.service.ArticleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class ArticleRestController {
    private final ArticleService articleService;

    @GetMapping("/search")
    public ResponseEntity<ResponseData<Object>> articleSearch(@ModelAttribute SearchRequest searchRequest) {
        searchRequest.defaultSearchValue();
        List<Article> articles = articleService.getArticle(searchRequest);

        ResponseData<Object> responseData = ResponseData.builder()
                .data(articles)
                .result(true)
                .build();

        return ResponseEntity.ok(responseData);
    }
}
