package com.example.board.dto.mappers;

import com.example.board.dto.CategoryDto;
import com.example.board.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CategoryMapper {
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    List<CategoryDto> toCategoryDtoList(List<Category> categories);

}
