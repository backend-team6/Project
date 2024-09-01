package com.yeonsu.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    public static final String url = "jdbc:mysql://127.0.0.1:3306/grepp"; //static은 메모리 아끼려고 (정적 멤버)
    public static final String user = "root";
    public static final String password = "";

    static{
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection() throws SQLException {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println("커넥션 생성 오류");
            e.printStackTrace();
        }
        return conn;
    }

    public static void close(AutoCloseable... closeables) { //개수 제한 없이 받아서 배열에 담아줌
        for (AutoCloseable closeable: closeables) {
            if (closeable != null) {
                try {
                    closeable.close();
                } catch (Exception e) {
                    System.out.println("close 하다가 에러나는 거 아직까지 못 보긴 했음.");
                    throw new RuntimeException();
                }
            }
        }
    }
}
