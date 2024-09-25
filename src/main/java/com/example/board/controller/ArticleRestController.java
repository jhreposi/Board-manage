package com.example.board.controller;

import com.example.board.dto.SearchRequest;
import com.example.board.global.response.ResponseData;
import com.example.board.model.Article;
import com.example.board.service.ArticleService;
import com.example.board.util.Page;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class ArticleRestController {
    private final ArticleService articleService;

    @GetMapping("/search")
    public ResponseEntity<ResponseData<Object>> articleSearch(@ModelAttribute SearchRequest searchRequest) {
        searchRequest.defaultSearchValue();
        //요청 uri에서 게시판 이름으로 boardType추출하기 추출로 category Ids 가져오기

        int articleCount = articleService.getArticleCount(searchRequest);
        Page<Article> articlePage = articleService.getPagingArticleList(searchRequest, articleCount);

        ResponseData<Object> responseData = ResponseData.builder()
                .data(articlePage)
                .result(true)
                .build();

        return ResponseEntity.ok(responseData);
    }
}
