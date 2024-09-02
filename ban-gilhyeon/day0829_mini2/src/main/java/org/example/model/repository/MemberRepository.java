package org.example.model.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.model.dto.MemberDTO;

import java.sql.SQLException;

@Mapper
public interface MemberRepository {
    String selectOne(@Param("user_id") String user_id, @Param("user_pw") String user_pw) throws SQLException;

    int insert(MemberDTO member) throws SQLException;
}
