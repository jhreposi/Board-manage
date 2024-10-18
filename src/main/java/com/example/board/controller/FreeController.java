package com.example.board.controller;

import com.example.board.dto.*;
import com.example.board.dto.free.FreeBoardDto;
import com.example.board.dto.free.FreeRequestDto;
import com.example.board.dto.mappers.ArticleMapper;
import com.example.board.dto.mappers.FileMapper;
import com.example.board.global.response.ResponseData;
import com.example.board.model.Article;
import com.example.board.model.Board;
import com.example.board.model.FileVo;
import com.example.board.service.ArticleService;
import com.example.board.service.FileService;
import com.example.board.service.SessionHelper;
import com.example.board.util.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Slf4j
@Controller
public class FreeController extends ArticleController{
    ArticleService articleService;
    FileService fileService;
    SessionHelper sessionHelper;

    public FreeController(ArticleService articleService,FileService fileService, SessionHelper sessionHelper) {
        super(articleService);
        this.articleService = articleService;
        this.fileService = fileService;
        this.sessionHelper = sessionHelper;
    }

    @GetMapping("/free")
    public String freeBoardView(SearchRequest searchRequest, Model model) {
        searchRequest.defaultSearchValue();
        searchRequest.setBoardType(Board.FREE.getBoardType());

        List<CategoryDto> categories = getCategories(Board.FREE.getBoardType());

        int articleCount = articleService.getArticleCount(searchRequest);

        Page<Article> pagingArticle = articleService.getPagingArticleList(searchRequest, articleCount);
        PageResponse page = ArticleMapper.INSTANCE.toPageGroupDto(pagingArticle.getPageGroup());
        List<FreeBoardDto> freeArticles = ArticleMapper.INSTANCE.toFreeBoardDto(pagingArticle.getArticles());

        model.addAttribute("search", searchRequest);
        model.addAttribute("categories", categories);
        model.addAttribute("page", page);
        model.addAttribute("articles", freeArticles);

        return "view/free";
    }

    @GetMapping("/free/{articleId}")
    public String freeArticleDetail(@PathVariable("articleId") int articleId, Model model) {
        Article freeArticle = articleService.getArticleDetail(articleId);
        ArticleResDto.ArticleDetail articleDetail = ArticleMapper.INSTANCE.toArticleDetailDto(freeArticle);

        List<FileVo> fileVos = fileService.getFilesByArticleId(articleId);

        List<FileResDto> responseFiles = FileMapper.INSTANCE.toFileDtoFrom(fileVos);

        model.addAttribute("article", articleDetail);
        model.addAttribute("files", responseFiles);

        return "view/freeDetail";
    }

    @GetMapping("/free/form")
    public String freeBoardForm(Model model, @RequestParam(value = "articleId", required = false) Integer articleId) {
//        if (sessionHelper.getAdminInfo() == null) {
//            return "redirect:/login";
//        }
        List<CategoryDto> categories = getCategories(Board.FREE.getBoardType());
        model.addAttribute("categories", categories);

        //article Id가 있다면 수정 페이지로 해당 article 정보를 가져 온다
        model.addAttribute("article", getArticleDetail(articleId));

        return "view/freeForm";
    }

    @ResponseBody
    @PostMapping("/free/form")
    public ResponseEntity<ResponseData<Object>> freeBoardFormSave(@RequestPart("files") MultipartFile[] multipartFiles,
                                            @ModelAttribute FreeRequestDto freeRequestDto) throws IOException {

        Article article = ArticleMapper.INSTANCE.toArticleBy(freeRequestDto);

        int createdArticleId = fileService.createArticleWithFiles(article, multipartFiles);

        ResponseData<Object> responseData = ResponseData.builder()
                .result(true)
                .message("게시글이 생성되었습니다")
                .data(createdArticleId)
                .build();

        return ResponseEntity.ok(responseData);
    }

}
