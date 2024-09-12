package com.example.board.controller;

import com.example.board.dto.AdminRes;
import com.example.board.dto.ArticleReqDto;
import com.example.board.dto.ArticleResDto;
import com.example.board.model.Article;
import com.example.board.model.BoardName;
import com.example.board.model.Category;
import com.example.board.service.ArticleService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/")
public class ArticleController {
    ArticleService articleService;
    ModelMapper modelMapper;

    public ArticleController(ArticleService articleService, ModelMapper modelMapper) {
        this.articleService = articleService;
        this.modelMapper = modelMapper;
    }

    @ModelAttribute("adminInfo")
    public AdminRes.InfoDto adminInfo(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        AdminRes.InfoDto adminInfo = null;
        // TODO: 2024-09-12 로그인페이지 말고 모든 페이지의 레이아웃이 있는데 매번 페이지 호출할떄마다 세션값 확인하는것이 합리적인가?

        if (session != null) {
            adminInfo = (AdminRes.InfoDto)session.getAttribute("adminInfo");
        }
        return adminInfo;
    }

    @GetMapping("notice")
    public String getArticle(Model model) {
        List<Article> article = articleService.getArticle();

        ModelMapper modelMapper = new ModelMapper();

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
    @PostMapping("notice/create")
    public ResponseEntity<?> createNotice(@Valid ArticleReqDto.NoticePost request) {
        // TODO: 2024-09-05 Article로 변환 후 insert
        /**
         * 게시글 등록할때의 사용자 정보를 매변 dto에 담아오기보다는 로그인시 세션으로 인스턴스화?해서
         * 로그인 정보는 바로 가져오자
         * id, name
         */
        log.info("article request => {}", request.toString());


        return ResponseEntity.ok(null   );
    }

}
