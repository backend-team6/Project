package com.grepp.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface UserRepository {

    int Login(UserDTO user) throws SQLException;
    int signUp(UserDTO user) throws SQLException;
    String getUserName(String id) throws SQLException;
    String getId(int no) throws SQLException;
}
