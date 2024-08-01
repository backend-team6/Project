package Daiso;

import java.sql.*;
import java.util.List;
import java.util.Map;

public class DaisoManagerImpl implements DaisoManager{
   /* try{
        Connection conn =  Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/workshop", "root", "1234");

        String SQL = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int result = 0;
    }*/


    @Override
    public int addProduct(Product p)  {
        int result = 0;
        try{
            Connection conn  = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/workshop", "root", "1234");
            PreparedStatement pstmt = null;
            String SQL = "insert into product values(?,?,?,?,?,?)";
            pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, p.getProductName());
            pstmt.setString(2,p.getCategory());
            pstmt.setInt(3,p.getProductPrice());
            pstmt.setString(4,p.getEvent());
            pstmt.setInt(5,p.getProductStock());
            pstmt.setDate(6,p.getArrivalDate());
            result = pstmt.executeUpdate();
        } catch (SQLException ex){
            ex.printStackTrace();
        }finally {
            return result;

        }
    }

    @Override
    public List<Product> getAllProduct() {
        
    }

    @Override
    public Product searchByProductName(String name) {
        return null;
    }

    @Override
    public List<Product> getProductsByPrice() {
        return List.of();
    }

    @Override
    public int updateProduct(int productId, Map<String, String> changeData) {
        return 0;
    }

    @Override
    public int deleteProduct(int productId) {
        return 0;
    }

}
