package product.manager;

import product.domain.Product;
import product.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DaisoManagerImpl implements DaisoManager {

    private static DaisoManager daisoManager;

    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;

    private DaisoManagerImpl() {
    }


    public static DaisoManager getInstance() {
        if (daisoManager == null) {
            synchronized (DaisoManagerImpl.class) {
                if (daisoManager == null)
                    daisoManager = new DaisoManagerImpl();
            }
        }
        return daisoManager;
    }

    @Override
    public int addProduct(Product p) {
        int result = -1;
        try {
            conn = DBUtil.getConnection();
            String sql = "insert into product(product_name, category, product_price, event, product_stock, arrival_date) values (?,?,?,?,?,?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,p.getProductName());
            pstmt.setString(2,p.getCategory());
            pstmt.setInt(3,p.getProductPrice());
            pstmt.setString(4, p.getEvent());
            pstmt.setInt(5, p.getProductStock());
            pstmt.setDate(6, p.getArrivalDate());

            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("SQL 오류");
        } finally {
            DBUtil.close(pstmt, conn);
        }
        return result;
    }

    @Override
    public List<Product> getAllProduct() {
        List<Product> productList = new ArrayList<>();
        try {
            conn = DBUtil.getConnection();
            String sql = "select product_id, product_name, category, product_price, event, product_stock, arrival_date from product";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                String category = rs.getString(3);
                int price = rs.getInt(4);
                String event = rs.getString(5);
                int stock = rs.getInt(6);
                Date date = rs.getDate(7);
                productList.add(new Product(id, name, category, price, event, stock, date));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtil.close(rs, pstmt, conn);
        }
        return productList;
    }

    @Override
    public Product searchByProductName(String name) {
        Product product = null;
        try {
            conn = DBUtil.getConnection();
            String sql = "select product_id, product_name, category, product_price, event, product_stock, arrival_date from product" +
                    " where product_name = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String productName = rs.getString(2);
                String category = rs.getString(3);
                int price = rs.getInt(4);
                String event = rs.getString(5);
                int stock = rs.getInt(6);
                Date date = rs.getDate(7);
                product = new Product(id, productName, category, price, event, stock, date);
                break;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtil.close(rs, pstmt, conn);
        }
        return product;
    }

    @Override
    public List<Product> getProductsByPrice() {
        List<Product> productList = new ArrayList<>();
        try {
            conn = DBUtil.getConnection();
            String sql = "select product_id, product_name, category, product_price, event, product_stock, arrival_date from product" +
                    " order by product_price asc;";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                String category = rs.getString(3);
                int price = rs.getInt(4);
                String event = rs.getString(5);
                int stock = rs.getInt(6);
                Date date = rs.getDate(7);
                productList.add(new Product(id, name, category, price, event, stock, date));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtil.close(rs, pstmt, conn);
        }
        return productList;
    }

    @Override
    public int updateProduct(int productId, Map<String, String> changeData) {
        Product product = null;
        int result = -1;
        try {
            conn = DBUtil.getConnection();
            int count = changeData.size();
            String sql = "update product set ";
            count = 1;
            boolean isNeedRest = false;
            for (String key : changeData.keySet()) {
                if (isNeedRest)
                    sql += ",";
                sql += key +"=";
                if (key.equals("product_id") || key.equals("product_price") || key.equals("product_stock")) {
                    sql += changeData.get(key);
                }
                if (key.equals("arrival_date")) {
                    sql += changeData.get(key);
                }
                if (key.equals("product_name") || key.equals("category") || key.equals("event")) {
                    sql += "'" + changeData.get(key)+ "'";
                }
                isNeedRest = true;
            }
            sql += " where product_id = " + productId;
            pstmt = conn.prepareStatement(sql);
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtil.close(pstmt, conn);
        }
        return result;
    }

    @Override
    public int deleteProduct(int productId) {
        Product product = null;
        int result = -1;
        try {
            conn = DBUtil.getConnection();
            String sql = "delete from product where product_id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, productId);
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtil.close(pstmt, conn);
        }
        return result;
    }
}