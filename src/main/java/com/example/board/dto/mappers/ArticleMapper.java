package com.example.board.dto.mappers;

import com.example.board.dto.ArticleResDto;
import com.example.board.dto.PageResponse;
import com.example.board.model.Article;
import com.example.board.util.Page;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ArticleMapper {
    ArticleMapper INSTANCE = Mappers.getMapper(ArticleMapper.class);


    List<ArticleResDto.NoticeList> toNoticeBodyDto(List<Article> articles);

    //List를 mapping할 때 커스텀맵핑을 하려면 메서드명을 같은 이름으로 하면 mapper가 사용한다
    @Mapping(target = "adminName", source = "article.admin.name")
    @Mapping(target = "categoryName", source = "article.category.name")
    ArticleResDto.NoticeList toNoticeBodyDto(Article article);


    @Mapping(target = "author", expression = "java(chooseAuthor(article))")
    @Mapping(target = "categoryName", source = "article.category.name")
    ArticleResDto.ArticleDetail toArticleDetailDto(Article article);

    PageResponse toPageGroupDto(Page<Article>.PageGroup pageGroup);


    default String chooseAuthor(Article article) {
        if (article.getMember() != null) {
            return article.getMember().getUsername();
        } else if (article.getAdmin() != null) {
            return article.getAdmin().getName();
        }
        return "unknown";
    }

}
