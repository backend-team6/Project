package daiso;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DaisoManagerImpl implements DaisoManeger{
    static Connection conn=null;
    String SQL=null;
    PreparedStatement pstmt=null;
    ResultSet rs=null;
    int result=0;

    public static void main(String[] args) {
    }

    void dbConnection(){
        try {
            conn= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/daiso","root","k99463708");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    void dbClose(){
        if(rs!=null){
            try {
                rs.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        if(pstmt!=null){
            try {
                pstmt.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        if(conn!=null){
            try {
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public int addProduct(Product p) {
        dbConnection();
        SQL="insert into product( product_name, category, product_price, event, product_stock, arrival_date)"
                + "values(?,?,?,?,?,?)";
        try {
            pstmt=conn.prepareStatement(SQL);
            pstmt.setString(1, p.getProductName());
            pstmt.setString(2, p.getCategory());
            pstmt.setInt(3,p.getProductPrice());
            pstmt.setString(4, p.getEvent());
            pstmt.setInt(5,p.getProductStock());
            pstmt.setDate(6,p.getArrivalDate());

            result=pstmt.executeUpdate(); //int 타입

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            dbClose();
        }
        return result;
    }

    @Override
    public List<Product> getAllProduct() {
        dbConnection();
        List<Product> result=new ArrayList<>();

        try {
            SQL="select * from product";
            pstmt=conn.prepareStatement(SQL); //statemt는 이렇게 미리 SQL을 넣지 않고 execute 하는 시점에 넣는 차이가 있음

            rs=pstmt.executeQuery();

            while(rs.next()){
                int pID=rs.getInt("product_id");
                String pName=rs.getString("product_name");
                String category=rs.getString("category");
                int price=rs.getInt("product_price");
                String event=rs.getString("event");
                int stock=rs.getInt("product_stock");
                Date arrivalDate=rs.getDate("arrival_date");
                Product product=new Product(pID, pName, category, price, event, stock, arrivalDate);
                result.add(product);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            dbClose();
        }

        return result;
    }

    @Override
    public Product searchByProductName(String name) {
        dbConnection();
        Product product=null;
        try{
            SQL="select * from product where product_name=(?)";
            pstmt=conn.prepareStatement(SQL);
            pstmt.setString(1, name);
            rs=pstmt.executeQuery();

            while(rs.next()) {
                String pName = rs.getString("product_name");
                String category = rs.getString("category");
                int Pprice = rs.getInt("product_price");
                String event = rs.getString("event");
                int pStock = rs.getInt("product_stock");
                Date date = rs.getDate("arrival_date");
                product = new Product(pName, category, Pprice, event, pStock, date);
            }

        } catch (SQLException e){
            e.printStackTrace();
        }finally {
            dbClose();
        }

        return product;
    }

    @Override
    public List<Product> getProductsByPrice() {
        dbConnection();
        List<Product> result=new ArrayList<>();

        //가격 오름차순으로 정렬한 상품 리스트 출력
        try{
            SQL="select * from product order by product_price asc";
            pstmt=conn.prepareStatement(SQL);
            rs=pstmt.executeQuery();

            while(rs.next()){
                String pName=rs.getString("product_name");
                String category=rs.getString("category");
                int price=rs.getInt("product_price");
                String event=rs.getString("event");
                int stock=rs.getInt("product_stock");
                Date date=rs.getDate("arrival_date");
                Product product=new Product(pName,category,price,event,stock,date);
                result.add(product);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            dbClose();
        }

        return result;
    }

    @Override
    public int updateProduct(int productId, Map<String, String> changeData) {
        dbConnection();

//        try{
//            SQL="update from ";
//        }catch (SQLException e){
//            e.printStackTrace();
//        }finally {
//            dbClose();
//        }
        return 0;
    }

    @Override
    public int deleteProduct(int productId) {
        dbConnection();
        int result=0;
        try{
            SQL="delete from product where product_id=(?)";
            pstmt=conn.prepareStatement(SQL);
            pstmt.setInt(1,productId);
            result=pstmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            dbClose();
        }
        return result;
    }
}
