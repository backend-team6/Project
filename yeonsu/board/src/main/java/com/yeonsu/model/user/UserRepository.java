package com.yeonsu.model.user;

import java.sql.SQLException;

public interface UserRepository {
    int insert(UserDTO user) throws SQLException;
    int update(UserDTO user) throws SQLException;
    int delete(int uno) throws SQLException;
    UserDTO selectOne(String loginId) throws SQLException;

    UserDTO selectOneByUno(int uno) throws SQLException;
}
