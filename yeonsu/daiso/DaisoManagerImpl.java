package daiso;

import java.sql.*;
import java.sql.Date;
import java.util.*;

public class DaisoManagerImpl implements DaisoManager {
    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;

    @Override
    public int addProduct(Product p) {
        int result;

        try {
            conn = DBUtil.getConnection();
            String SQL = "insert into product(product_name, category, product_price, event, product_stock, arrival_date) values (?,?,?,?,?,?)";

            pstmt = conn.prepareStatement(SQL); //Statement는 이렇게 미리 SQL을 넣지 않고 execute 하는 시점에 넣는 차이가 있음
            pstmt.setString(1, p.getProductName());
            pstmt.setString(2, p.getCategory());
            pstmt.setInt(3, p.getProductPrice());
            pstmt.setString(4, p.getEvent());
            pstmt.setInt(5, p.getProductStock());
            pstmt.setDate(6, p.getArrivalDate());

            result = pstmt.executeUpdate(); //SQL문이 select라면 exectueQuery() 호출
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            DBUtil.close(pstmt, conn);
        }
        return result;
    }

    @Override
    public List<Product> getAllProduct() {
        List<Product> result = new ArrayList<>();

        try {
            conn = DBUtil.getConnection();
            String SQL = "select * from product";

            pstmt = conn.prepareStatement(SQL);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                int productId = rs.getInt("product_id");
                String productName = rs.getString("product_name");
                String category = rs.getString("category");
                int productPrice = rs.getInt("product_price");
                String event = rs.getString("event");
                int productStock = rs.getInt("product_stock");
                Date arrivalDate = rs.getDate("arrival_date");
                Product p = new Product(productId, productName, category, productPrice, event, productStock, arrivalDate);
                result.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            DBUtil.close(rs, pstmt, conn);
        }
        return result;
    }

    @Override
    public Product searchByProductName(String name) {
        Product result = null;

        try {
            conn = DBUtil.getConnection();
            String SQL = "select * from product where product_name = '" + name + "'";

            pstmt = conn.prepareStatement(SQL);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                int productId = rs.getInt("product_id");
                String productName = rs.getString("product_name");
                String category = rs.getString("category");
                int productPrice = rs.getInt("product_price");
                String event = rs.getString("event");
                int productStock = rs.getInt("product_stock");
                Date arrivalDate = rs.getDate("arrival_date");
                result = new Product(productId, productName, category, productPrice, event, productStock, arrivalDate);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            DBUtil.close(rs, pstmt, conn);
        }
        return result;
    }

    @Override
    public List<Product> getProductsByPrice() {
        List<Product> result = new ArrayList<>();

        try {
            conn = DBUtil.getConnection();
            String SQL = "select * from product order by product_price";
            pstmt = conn.prepareStatement(SQL);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                int productId = rs.getInt("product_id");
                String productName = rs.getString("product_name");
                String category = rs.getString("category");
                int productPrice = rs.getInt("product_price");
                String event = rs.getString("event");
                int productStock = rs.getInt("product_stock");
                Date arrivalDate = rs.getDate("arrival_date");
                Product p = new Product(productId, productName, category, productPrice, event, productStock, arrivalDate);
                result.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            DBUtil.close(rs, pstmt, conn);
        }
        return result;
    }

    @Override
    public int updateProduct(int productId, Map<String, String> changeData) {
        int result;

        try {
            conn = DBUtil.getConnection();

            StringBuilder SQL = new StringBuilder("update product set ");

            Set<String> keySet = changeData.keySet();
            Iterator<String> iterator = keySet.iterator();
            while(iterator.hasNext()) {
                String column = iterator.next();
                String content = changeData.get(column);

                if (column.equals("product_name") || column.equals("category") || column.equals("event")) {
                    SQL.append(column).append(" = ").append("'").append(content).append("'");
                } else {
                    SQL.append(column).append(" = ").append(content);
                }
                if (iterator.hasNext()) {
                    SQL.append(", ");
                }
            }

            SQL.append(" WHERE product_id = ").append(productId);
            pstmt = conn.prepareStatement(SQL.toString());
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            DBUtil.close(pstmt, conn);
        }
        return result;
    }

    @Override
    public int deleteProduct(int productId) {
        int result;

        try {
            conn = DBUtil.getConnection();
            String SQL = "delete from product where product_id = '" + productId + "'";
            pstmt = conn.prepareStatement(SQL);
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            DBUtil.close(pstmt, conn);
        }
        return result;
    }
}
