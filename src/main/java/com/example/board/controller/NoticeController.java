package com.example.board.controller;

import com.example.board.dto.*;
import com.example.board.global.response.ResponseData;
import com.example.board.model.Article;
import com.example.board.model.Board;
import com.example.board.service.ArticleService;
import com.example.board.service.SessionHelper;
import com.example.board.util.Page;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Slf4j
@Controller
public class NoticeController extends ArticleController {
    SessionHelper sessionHelper;
    ArticleService articleService;
    ModelMapper modelMapper;

    public NoticeController(SessionHelper sessionHelper, ArticleService articleService, ModelMapper modelMapper) {
        super(articleService, modelMapper);
        this.sessionHelper = sessionHelper;
        this.articleService = articleService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/notice")
    public String getArticle(SearchRequest searchRequest, Model model) {
        searchRequest.defaultSearchValue();

        List<CategoryDto> categories = getCategories(Board.NOTICE.getBoardType());
        List<Integer> categoryIds = categories.stream().map(CategoryDto::getCategoryId).toList();
        searchRequest.setCategoryIds(categoryIds);

        int articleCount = articleService.getArticleCount(searchRequest);
        Page<Article> articlePage = articleService.getPagingArticleList(searchRequest, articleCount);

        PageResponse page = modelMapper.map(articlePage.getPageGroup(), PageResponse.class);

        List<ArticleResDto.NoticeList> notices = articlePage.getArticles().stream().map(notice -> modelMapper
                        .map(notice, ArticleResDto.NoticeList.class))
                        .toList();


        model.addAttribute("categories", categories);
        model.addAttribute("page", page);
        model.addAttribute("articles", notices);

        return "view/notice";
    }

    @GetMapping("/notice/form")
    public String createNotice(Model model, @RequestParam(value = "articleId", required = false) Integer articleId) {
        if (sessionHelper.getAdminInfo() == null) {
            return "redirect:/login";
        }
        List<CategoryDto> categories = getCategories(Board.NOTICE.getBoardType());

        model.addAttribute("categories", categories);

        //article Id가 있다면 수정 페이지로 해당 article 정보를 가져온다
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
        // 새글 작성시에는 form에 name값이 articleId인 input 태그가 없어 기본생성자 초기화 값인 0으로
        // 새글, 수정 작업을 결정한다
        boolean isNewArticle = noticeRequest.getArticleId() == 0;

        int responseArticleId = 0;
        String message = null;

        if (isNewArticle) {
            responseArticleId = articleService.createArticle(article);
            message = "게시글 생성 완료";
        } else {
            articleService.modifyArticle(article);
            responseArticleId = noticeRequest.getArticleId();
            message = "게시글 수정 완료";
        }
        ResponseData<Object> response = ResponseData.builder()
                .result(true)
                .data(responseArticleId)
                .message(message)
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/notice/{articleId}")
    public String getNoticeDetail(@PathVariable("articleId") int articleId, Model model) {
        Article article = articleService.getArticleDetail(articleId);
        ArticleResDto.ArticleDetail articleDetail = modelMapper.map(article, ArticleResDto.ArticleDetail.class);

        model.addAttribute("article", articleDetail);
        return "view/noticeDetail";
    }

    @ResponseBody
    @DeleteMapping("/notice")
    public ResponseEntity<Void> removeNotice(
            @ModelAttribute(value = "articleId") int articleId) {
        if (sessionHelper.getAdminInfo() == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        articleService.removeArticle(articleId);

        return ResponseEntity.noContent().build();
    }

}
