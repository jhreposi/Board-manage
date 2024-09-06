package com.example.board.controller;

import com.example.board.dto.ArticleReqDto;
import com.example.board.model.Article;
import com.example.board.service.ArticleService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

        article.stream().forEach(a -> {
            System.out.println(a.toString());
        });

        ModelMapper modelMapper = new ModelMapper();
        // TODO: 2024-09-05 ArticleResDto 만들고 변환해서 넘기기

//        List<ArticleReqDto.Notice> notices = article.stream()
//                        .map(notice -> modelMapper.map(notice, ArticleReqDto.Notice.class))
//                        .collect(Collectors.toList());

        model.addAttribute("articles", article);
        return "view/notice";
    }

    @GetMapping("/notice/new")
    public String createNotice() {
        // TODO: 2024-09-05 boardType, boardName 리스트 넘겨서 selectBox 배치
        return "view/noticeCreate";
    }
    @PostMapping("/notice/create")
    public ResponseEntity<?> createNotice(ArticleReqDto.Notice reqArticle) {
        // TODO: 2024-09-05 Article로 변환 후 insert
        log.info("article request => {}", reqArticle.toString());

        return ResponseEntity.ok(null   );
    }

}
