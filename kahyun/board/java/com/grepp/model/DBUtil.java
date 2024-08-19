package com.grepp.model;

import com.mysql.cj.jdbc.Driver;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {

    private static final String url="jdbc:mysql://127.0.0.1:3306/workshop";
    private static final String user="devcourse";
    private static final String password="";

    static{
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch (ClassNotFoundException e){
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection()throws SQLException{
        Connection conn=null;
        try{
            conn= DriverManager.getConnection(url,user,password);
        }catch (SQLException e){
            System.out.println("커넥션 생성 오류");
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return conn;
    }

    public static void close(AutoCloseable... closeables)throws SQLException{
        for(AutoCloseable closeable:closeables){
            if(closeable!=null){
                try{
                    closeable.close();
                }catch (Exception e){
                    System.out.println("close 오류");
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
