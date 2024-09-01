package com.yeonsu.model.user;

import com.yeonsu.model.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepositoryMysql implements UserRepository {
    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;

    private UserRepositoryMysql() {}
    private static UserRepositoryMysql instance = new UserRepositoryMysql();
    public static UserRepositoryMysql getInstance() {
        return instance;
    }

    @Override
    public int insert(UserDTO user) throws SQLException {
        int result = 0;
        try {
            String sql = "INSERT INTO USER_TB(loginid, loginpw) VALUES (?, ?)";
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, user.getLoginId());
            ps.setString(2, user.getLoginPw());
            result = ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("insert error");
            throw ex;
        } finally {
            DBUtil.close(ps, conn);
        }
        return result;
    }

    @Override
    public int update(UserDTO user) throws SQLException {
        return 0;
    }

    @Override
    public int delete(int uno) throws SQLException {
        return 0;
    }

    @Override
    public UserDTO selectOne(String loginId) throws SQLException {
        UserDTO user = null;

        try {
            String sql = "SELECT UNO, LOGINID, LOGINPW FROM USER_TB WHERE LOGINID = \"" + loginId + "\"";

            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                user = new UserDTO(rs.getInt("UNO"), rs.getString("LOGINID"), rs.getString("LOGINPW"));
            } else {
                return null;
            }
        } catch (SQLException ex) {
            System.out.println("select error");
            throw ex;
        } finally {
            DBUtil.close(rs, ps, conn);
        }

        return user;
    }

    @Override
    public UserDTO selectOneByUno(int uno) throws SQLException {
        UserDTO user = null;

        try {
            String sql = "SELECT UNO, LOGINID, LOGINPW FROM USER_TB WHERE UNO = " + uno;

            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            if (rs.next()) {
                user = new UserDTO(rs.getInt("UNO"), rs.getString("LOGINID"), rs.getString("LOGINPW"));
            }
        } catch (SQLException ex) {
            System.out.println("select error");
            throw ex;
        } finally {
            DBUtil.close(rs, ps, conn);
        }

        return user;
    }
}
