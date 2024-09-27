package com.example.board.dto.mappers;

import com.example.board.dto.AdminRes;
import com.example.board.model.Admin;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AdminMapper {
    AdminMapper INSTANCE = Mappers.getMapper(AdminMapper.class);

    AdminRes.InfoDto toAdminInfoDto(Admin responseAdmin);

}
