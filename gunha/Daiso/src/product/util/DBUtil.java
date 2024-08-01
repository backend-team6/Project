package product.util;

import product.constant.DBUser;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    private static final DBUser user = DBUser.ROOT;

    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(user.getUrl(), user.getId(), user.getPassword());
        } catch (SQLException e) {
            System.out.println("커넥션 생성 오류");
            e.printStackTrace();
        }
        return conn;
    }

    public static void close(AutoCloseable... closeables) {
        for (AutoCloseable closeable : closeables) {
            if (closeable != null) {
                try {
                    closeable.close();
                } catch (Exception e) {
                    System.out.println("close 중 에러");
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
