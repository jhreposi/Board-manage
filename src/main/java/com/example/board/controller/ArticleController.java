package com.example.board.controller;

import com.example.board.dto.AdminRes;
import com.example.board.dto.ArticleReqDto;
import com.example.board.dto.ArticleResDto;
import com.example.board.model.Article;
import com.example.board.service.ArticleService;
import jakarta.servlet.http.HttpServletRequest;
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
    public AdminRes.InfoDto adminInfo(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        AdminRes.InfoDto adminInfo = null;
        // TODO: 2024-09-12 로그인페이지 말고 모든 페이지의 레이아웃이 있는데 매번 페이지 호출할떄마다 세션값 확인하는것이 합리적인가?
        // 아니면 처음 로그인하면 세션스토리지에 저장해서 하는것 고려
        /**
         * 여기서 httpsession을 생성을 해버려서
         * 로그인 할시 session.isNew()가 계속 false였음
         * 아티클 페이지 안타고 바로 로그인주소로 가서 로그인을 할떄만 isNew() true여서
         * 아티클 페이지 에서 로그인하면 session생성을 못해 레이아웃에 정보표시를 못했다
         * 1. 그전에 주입을 HttpSession으로 받아서 그런가? => X
         * HttpServletRequest주입받고
         * HttpSession session = request.getSession(); 로변경했지만 여기 거치고 로그인하면 isNew() false
         * 2. HttpSession session = request.getSession(false);
         * 로 변경해야 기존세션없으니 빈세션 생성을 안함
         */
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
