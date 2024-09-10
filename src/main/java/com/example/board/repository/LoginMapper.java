package com.example.board.repository;

import com.example.board.model.Admin;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface LoginMapper {

    Optional<Integer> selectValidAdmin(Admin admin);

    Optional<Admin> findByloginId(String loginId);

    Admin selectAdminById(int adminId);
}
