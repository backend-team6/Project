package Daiso;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;

public class DaisoJDBC {
    static Connection conn = null;
    static PreparedStatement pstmt = null;
    static ResultSet rs = null;
    public static void main(String[] args) {

        int menuNum = 0;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try{
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/workshop", "root", "1234");
            while (menuNum != 9) {
                DaisoJDBC.Menu();
                System.out.print("메뉴 선택 >> ");
                menuNum = Integer.parseInt(br.readLine());

                switch (menuNum) {
                    case 1:

                        break;
                    case 2:
                        //getAllProduct();
                        break;

                    case 3:
                        break;

                }
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            if(rs != null){
                try {
                    rs.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }

            if(pstmt != null){
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }

            if(conn != null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }

    } // main ---------------------------------------------

    static void Menu(){
        System.out.println("====메 뉴====");
        System.out.println("1. 상품 추가");
        System.out.println("2. 상품 전체 출력");
        System.out.println("3. 상품명으로 데이터 검색");
        System.out.println("4. 오름차순으로 데이터 출력");
        System.out.println("5. 상품 수정");
        System.out.println("6. 상품 삭제");
        System.out.println("9. 종 료");
        System.out.println("=================");
        ;
    }

    static void getAllProduct() throws SQLException {
        String SQL = "select * from product";
        pstmt = conn.prepareStatement(SQL);
        ResultSet rs = pstmt.executeQuery();

    }


}
