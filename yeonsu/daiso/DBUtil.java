package daiso;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    public static final String url = "jdbc:mysql://127.0.0.1:3306/daiso";
    public static final String user = "root";
    public static final String password = "NewSt@rt!70";

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
                    System.out.println("close error");
                    throw new RuntimeException();
                }
            }
        }
    }
}
