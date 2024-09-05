package com.example.board;

import com.example.board.dto.ArticleInfoDto;
import com.example.board.model.Article;
import com.example.board.model.Category;
import com.example.board.repository.ArticleMapper;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BoardAdminApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    public void toDto() {

        Category category = Category.builder()
                .categoryId(2)
                .name("공지")
                .build();

        Article article = Article.builder()
                .articleId(1)
                .title("제목")
                .createdAt("2024")
                .content("내용")
                .pinnedYn("Y")
                .secretYn("N")
                .category(category)
                .build();





        ModelMapper modelMapper = new ModelMapper();

//        modelMapper.addMappings(new PropertyMap<ArticleInfo, ArticleInfoDto>() {
//            @Override
//            protected void configure() {
//                map(source.getArticle().getArticleId(), destination.getArticleId());
//
//            }
//        });

//        TypeMap<ArticleInfo, ArticleInfoDto> typeMap = modelMapper.createTypeMap(ArticleInfo.class, ArticleInfoDto.class);
//        typeMap.addMappings(mapper -> mapper.map(src -> src.getBoard().getBoardId(), ArticleInfoDto::setBoardId ));

        ArticleInfoDto articleInfoDto = modelMapper.map(article, ArticleInfoDto.class);

        System.out.println(articleInfoDto.toString());



    }
    @Test
    public void mapping(ArticleMapper mapper) {
        mapper.selectNoticeList().stream().forEach(article -> {
            System.out.println(article.toString());
        });
    }

}
