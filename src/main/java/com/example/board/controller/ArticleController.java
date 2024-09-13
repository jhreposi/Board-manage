package com.example.board.controller;

import com.example.board.dto.AdminRes;
import com.example.board.dto.ArticleReqDto;
import com.example.board.dto.ArticleResDto;
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

@Controller
@Slf4j
@RequestMapping("/")
public class ArticleController {
    SessionHelper sessionHelper;
    ArticleService articleService;
    ModelMapper modelMapper;

    public ArticleController(SessionHelper sessionHelper, ArticleService articleService, ModelMapper modelMapper) {
        this.sessionHelper = sessionHelper;
        this.articleService = articleService;
        this.modelMapper = modelMapper;
    }

    @ModelAttribute("adminInfo")
    public AdminRes.InfoDto adminInfo() {
        return sessionHelper.getAdminInfo();
    }

    @GetMapping("notice")
    public String getArticle(Model model) {
        List<Article> article = articleService.getArticle();

        List<ArticleResDto.NoticeList> notices = article.stream().map(notice -> modelMapper
                        .map(notice, ArticleResDto.NoticeList.class))
                        .toList();

        model.addAttribute("articles", notices);
        return "view/notice";
    }

    @GetMapping("admin/notice/new")
    public String createNotice(Model model) {
        int boardType = BoardName.NOTICE.getBoardType();
        List<Category> categoryList = articleService.getCategoriesBy(boardType);

        List<ArticleResDto.Category> categories = categoryList.stream()
                .map(category -> modelMapper
                        .map(category, ArticleResDto.Category.class))
                .toList();

        model.addAttribute("categories", categories);

        return "view/noticeCreate";
    }
    @ResponseBody
    @PostMapping("notice/create")
    public ResponseEntity<String> createNotice(@Valid ArticleReqDto.NoticePost noticeRequest) {
        noticeRequest.setAdminId(sessionHelper.getAdminInfo().getAdminId());

        Article article = Article.from(noticeRequest);
        articleService.createArticle(article);

        return ResponseEntity.ok("게시글 생성");
    }

}
