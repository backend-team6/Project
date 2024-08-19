package com.grepp.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class BoardRepositoryMysql implements BoardRepository{
    private BoardRepositoryMysql(){}
    private static BoardRepository instance=new BoardRepositoryMysql();
    public static BoardRepository getInstance(){
        return instance;
    }

    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;

    @Override
    public List<BoardDTO> selectAll() throws SQLException {
        List<BoardDTO> list=new LinkedList<>();
        try{
            String SQL="SELECT NO, WRITER, READ_COUNT,TITLE, CONTENT, REG_DATE FROM BOARD_TB";
            conn=DBUtil.getConnection();
            pstmt=conn.prepareStatement(SQL);
            rs=pstmt.executeQuery();
            while(rs.next()){
                list.add(makeBoardDTO(rs));
            }
        }catch (SQLException e){
            System.out.println("selectAll() 에러 발생");
            e.printStackTrace();
            throw e;
        }finally {
            DBUtil.close(rs, pstmt, conn);
        }

        return list;
    }

    @Override
    public BoardDTO selectOne(int no) throws SQLException {
        BoardDTO board=null;
        try{
            String SQL="SELECT NO, WRITER, READ_COUNT,TITLE, CONTENT, REG_DATE FROM BOARD_TB WHERE NO="+no;
            conn=DBUtil.getConnection();
            pstmt=conn.prepareStatement(SQL);
            rs=pstmt.executeQuery();
            while(rs.next()){
                board=makeBoardDTO(rs);
            }
        }catch (SQLException e){
            System.out.println("selectOne 오류");
            e.printStackTrace();
            throw e;
        }finally {
            DBUtil.close(rs, pstmt, conn);
        }

        return board;
    }

    @Override
    public int insert(BoardDTO board) throws SQLException {
        int result=0;

        try{
            String SQL="INSERT INTO BOARD_TB(WRITER, TITLE, CONTENT, REG_DATE) VALUES(?,?,?,NOW())";
            conn=DBUtil.getConnection();
            pstmt=conn.prepareStatement(SQL);
            pstmt.setString(1, board.getWriter());
            pstmt.setString(2, board.getTitle());
            pstmt.setString(3, board.getContent());
            result=pstmt.executeUpdate();
        }catch (SQLException e){
            System.out.println("insert 오류");
            e.printStackTrace();
            throw e;
        }finally {
            DBUtil.close(pstmt, conn);
        }

        return result;
    }

    @Override
    public int update(BoardDTO board) throws SQLException {
        int result=0;
        try {
            String SQL="UPDATE BOARD_TB SET";

            if(board.getTitle()!=null && board.getTitle().length()>0){
                SQL+=" TITLE = '"+board.getTitle()+"', ";
            }
            if(board.getWriter()!=null && board.getWriter().length()>0){
                SQL+=" WRITER = '"+board.getWriter()+"', ";
            }
            if(board.getContent()!=null && board.getContent().length()>0){
                SQL+= " CONTENT = '"+board.getContent()+"', ";
            }
            SQL=SQL.substring(0,SQL.length()-2);

            SQL+=" WHERE NO = "+board.getNo();

            conn=DBUtil.getConnection();
            pstmt=conn.prepareStatement(SQL);
            result =pstmt.executeUpdate();
        }catch (SQLException e){
            System.out.println("update 에러");
            throw e;
        }finally {
            DBUtil.close(pstmt,conn);
        }
        return result;
    }

    @Override
    public int pulsCount(int no) throws SQLException {
        int result=0;
        try {
            String SQL="UPDATE BOARD_TB SET READ_COUNT=READ_COUNT+1 WHERE NO ="+no;
            conn=DBUtil.getConnection();
            pstmt=conn.prepareStatement(SQL);
            result =pstmt.executeUpdate();
        }catch (SQLException e){
            System.out.println("plusCount 에러");
            throw e;
        }finally {
            DBUtil.close(pstmt,conn);
        }
        return result;
    }

    private BoardDTO makeBoardDTO(ResultSet rs)throws SQLException{
        BoardDTO boardDTO=new BoardDTO();
        boardDTO.setNo(rs.getInt("NO"));
        boardDTO.setTitle(rs.getString("TITLE"));
        boardDTO.setWriter(rs.getString("WRITER"));
        boardDTO.setContent(rs.getString("CONTENT"));
        boardDTO.setReadCount(rs.getInt("READ_COUNT"));
        boardDTO.setRegDate(rs.getString("REG_DATE"));
        return boardDTO;
    }
}
