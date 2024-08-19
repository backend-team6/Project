package com.grepp.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.ExecutionException;

public class UserRepositoryMysql implements UserRepository {

    private UserRepositoryMysql(){}
    private static UserRepository instance=new UserRepositoryMysql();
    public static UserRepository getInstance(){
        return instance;
    }

    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;

    @Override
    public int Login(UserDTO user) throws SQLException {
        int result=0;

        try{
            String SQL="SELECT * FROM USER WHERE ID=? AND PASSWORD=?";
            conn=DBUtil.getConnection();
            pstmt=conn.prepareStatement(SQL);
            pstmt.setString(1, user.getId());
            pstmt.setString(2, user.getPassword());
            rs=pstmt.executeQuery();
            if(rs.next()) result=1;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DBUtil.close(rs,pstmt,conn);
        }
        return result;
    }

    @Override
    public int signUp(UserDTO user) throws SQLException {
        int result=0;

        try{
            String SQL="INSERT INTO USER(ID, NAME, PASSWORD) VALUES(?, ?, ?)";
            conn=DBUtil.getConnection();
            pstmt=conn.prepareStatement(SQL);
            pstmt.setString(1, user.getId());
            pstmt.setString(2, user.getName());
            pstmt.setString(3, user.getPassword());
            result=pstmt.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DBUtil.close(pstmt,conn);
        }
        return result;
    }

    @Override
    public String getUserName(String id) throws SQLException {
        String Name=null;

        try{
            String SQL="SELECT NAME FROM USER WHERE ID='"+id+"'";
            conn=DBUtil.getConnection();
            pstmt= conn.prepareStatement(SQL);
            rs=pstmt.executeQuery();
            while(rs.next()){
                Name=rs.getString("NAME");
            }
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("getUserName 오류");
        }finally {
            DBUtil.close(rs,pstmt,conn);
        }
        return Name;
    }

    @Override
    public String getId(int no) throws SQLException {
        String id=null;
        try{
            String SQL="SELECT ID FROM BOARD_TB, USER WHERE BOARD_TB.WRITER=USER.NAME AND BOARD_TB.NO="+no;
            conn=DBUtil.getConnection();
            pstmt=conn.prepareStatement(SQL);
            rs= pstmt.executeQuery();
            if(rs.next()) id=rs.getString("ID");
        }catch (Exception e){
            System.out.println("getId오류 발생");
        }finally {
            DBUtil.close(rs,pstmt,conn);
        }

        return id;
    }
}
