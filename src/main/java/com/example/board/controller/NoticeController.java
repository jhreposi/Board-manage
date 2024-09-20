package com.example.board.controller;

import com.example.board.dto.ArticleReqDto;
import com.example.board.dto.ArticleResDto;
import com.example.board.global.response.ResponseData;
import com.example.board.model.Article;
import com.example.board.model.BoardName;
import com.example.board.model.Category;
import com.example.board.service.ArticleService;
import com.example.board.service.SessionHelper;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Slf4j
@Controller
public class NoticeController {
    SessionHelper sessionHelper;
    ArticleService articleService;
    ModelMapper modelMapper;

    public NoticeController(SessionHelper sessionHelper, ArticleService articleService, ModelMapper modelMapper) {
        this.sessionHelper = sessionHelper;
        this.articleService = articleService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/notice")
    public String getArticle(Model model) {
        List<Article> article = articleService.getArticle();

        List<ArticleResDto.NoticeList> notices = article.stream().map(notice -> modelMapper
                        .map(notice, ArticleResDto.NoticeList.class))
                        .toList();

        model.addAttribute("articles", notices);
        return "view/notice";
    }

    @GetMapping("/notice/form")
    public String createNotice(Model model, @RequestParam(value = "articleId", required = false) Integer articleId) {
        if (sessionHelper.getAdminInfo() == null) {
            return "redirect:/login";
        }
        int boardType = BoardName.NOTICE.getBoardType();
        List<Category> categoryList = articleService.getCategoriesBy(boardType);

        List<ArticleResDto.Category> categories = categoryList.stream()
                .map(category -> modelMapper
                        .map(category, ArticleResDto.Category.class))
                .toList();

        model.addAttribute("categories", categories);

        if (articleId != null) {
            Article article = articleService.getArticleDetail(articleId);
            ArticleResDto.ArticleDetail articleDetail = modelMapper.map(article, ArticleResDto.ArticleDetail.class);
            model.addAttribute("article", articleDetail);
        }

        return "view/noticeCreate";
    }
    @ResponseBody
    @PostMapping("/notice/form")
    public ResponseEntity<ResponseData<Object>> createNotice(@Valid ArticleReqDto.NoticePost noticeRequest) {
        noticeRequest.setAdminId(sessionHelper.getAdminInfo().getAdminId());

        Article article = Article.from(noticeRequest);
        boolean isNewArticle = noticeRequest.getAdminId() == 0;

        String message = null;

        if (isNewArticle) {
            articleService.createArticle(article);
            message = "게시글 생성 완료";
        } else {
            articleService.modifyArticle(article);
            message = "게시글 수정 완료";
        }
        ResponseData<Object> response = ResponseData.builder()
                .result(true)
                .data(article.getArticleId())
                .message(message)
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/notice/{articleId}")
    public String getNoticeDetail(@PathVariable("articleId") int articleId, Model model) {
        Article article = articleService.getArticleDetail(articleId);
        ArticleResDto.ArticleDetail articleDetail = modelMapper.map(article, ArticleResDto.ArticleDetail.class);
        log.info("view info {}", articleDetail.toString());

        model.addAttribute("article", articleDetail);
        return "view/noticeDetail";
    }

}
