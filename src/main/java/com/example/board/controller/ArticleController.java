package com.example.board.controller;

import com.example.board.dto.AdminRes;
import com.example.board.dto.ArticleReqDto;
import com.example.board.dto.ArticleResDto;
import com.example.board.model.Article;
import com.example.board.service.ArticleService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
@Slf4j
@RequestMapping("/")
public class ArticleController {
    ArticleService articleService;

    @ModelAttribute("adminInfo")
    public AdminRes.InfoDto adminInfo(HttpSession session) {
        if (session.getAttribute("adminInfo") != null) {
            return (AdminRes.InfoDto)session.getAttribute("adminInfo");
        }
        return null;
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
    public String createNotice() {
        // TODO: 2024-09-05 boardType, boardName 리스트 넘겨서 selectBox 배치

        return "view/noticeCreate";
    }
    @PostMapping("notice/create")
    public ResponseEntity<?> createNotice(ArticleReqDto.NoticePost requst) {
        // TODO: 2024-09-05 Article로 변환 후 insert
        /**
         * 게시글 등록할때의 사용자 정보를 매변 dto에 담아오기보다는 로그인시 세션으로 인스턴스화?해서
         * 로그인 정보는 바로 가져오자
         * id, name
         */
        log.info("article request => {}", requst.toString());


        return ResponseEntity.ok(null   );
    }

}
