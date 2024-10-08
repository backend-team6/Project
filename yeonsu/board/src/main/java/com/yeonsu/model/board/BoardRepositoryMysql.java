package com.yeonsu.model.board;

import com.yeonsu.model.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BoardRepositoryMysql implements BoardRepository {
    //    private static final String INSERT_SQL = "INSERT INTO BOARD_TB(TITLE, WRITER, CONTENT, READ_COUNT, REG_DATE) VALUES (?, ?, ?, NOW())";
    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;

    //싱글톤 패턴
    private BoardRepositoryMysql() {
    }

    private static BoardRepositoryMysql instance = new BoardRepositoryMysql();

    public static BoardRepository getInstance() {
        return instance;
    }

    @Override
    public int insert(BoardDTO board, int writer) throws SQLException {
        int result = 0;
        try {
            String sql = "INSERT INTO BOARD_TB(TITLE, WRITER, CONTENT, REG_DATE, READ_COUNT) VALUES (?, ?, ?, NOW(), ?)";
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, board.getTitle());
            ps.setInt(2, writer);
            ps.setString(3, board.getContent());
            ps.setInt(4, 0);
            result = ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("insert error");
            throw ex;
        } finally {
            DBUtil.close(ps, conn);
        }
        return result;
    }

//    @Override
//    public int update(BoardDTO board, String category) throws SQLException {
//        //이 update 버전은 수정하고 싶은 게 뭔지 정확히 찝어서 그것만 수정하기
//        int result = 0;
//
//        try {
//            String sql = "UPDATE BOARD_TB SET ";
//            if ("title".equals(category)) {
//                sql += " TITLE = " + board.getTitle();
//            } else if ("writer".equals(category)) {
//                sql += " WRITER = " + board.getWriter();
//            } else if ("content".equals(category)) {
//                sql += " CONTENT = " + board.getContent();
//            }
//            sql += "WHERE NO = " + board.getNo();
//            conn = DBUtil.getConnection();
//            ps = conn.prepareStatement(sql);
//            result = ps.executeUpdate();
//        } catch (SQLException ex) {
//            System.out.println("update error");
//            throw ex;
//        } finally {
//            DBUtil.close();
//        }
//        return result;
//    }

    @Override
    public int update(BoardDTO board) throws SQLException {
        // 위의 update 버전은 수정하고싶은게 뭔지 정확히 찝어서 그것만 수정하기였고
        // 이 update는 수정하고 싶은게 여러개 이면 한꺼번에 수정하도록 값의 유무 체크해서 진행하는 버전
        int result = 0;

        try {
            String sql = " UPDATE BOARD_TB SET ";

            if (board.getTitle() != null && board.getTitle().length() > 0) { // 제목에 변경하고자 값이 확실히 있는지 체크해서
                sql += " TITLE='" + board.getTitle() + "', ";
            }
//            if (board.getWriter() != null && board.getWriter().length() > 0) {
//                sql += " WRITER='" + board.getWriter() + "', ";
//            }
            if (board.getContent() != null && board.getContent().length() > 0) {
                sql += " CONTENT='" + board.getContent() + "', ";
            }
            sql = sql.substring(0, sql.length() - 2);

            sql += " WHERE NO = " + board.getNo();
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(sql);
            result = ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("update error");
            throw ex;
        } finally {
            DBUtil.close(ps, conn);
        }

        return result;
    }


    @Override
    public int delete(int no) throws SQLException {
        int result = 0;
        try {
            String sql = "DELETE FROM BOARD_TB WHERE NO = " + no;
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(sql);
            result = ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("delete error");
            throw ex;
        } finally {
            DBUtil.close(ps, conn);
        }
        return result;
    }

    @Override
    public List<BoardDTO> selectAll() throws SQLException {
        List<BoardDTO> list = new ArrayList<>();
        conn = DBUtil.getConnection();

        try {
            String sql = "SELECT NO, TITLE, WRITER, CONTENT, READ_COUNT, REG_DATE FROM BOARD_TB";

            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                list.add(makeBoardDTO(rs));
            }
        } catch (SQLException ex) {
            System.out.println("select error");
            throw ex;
        } finally {
            DBUtil.close(rs, ps, conn);
        }

        return list;
    }

    @Override
    public BoardDTO selectOne(int no) throws SQLException {
        BoardDTO board = null;

        try {
            String sql = "SELECT NO, TITLE, WRITER, CONTENT, READ_COUNT, REG_DATE FROM BOARD_TB WHERE NO = " + no;

            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            if (rs.next()) { //글번호 이상하면 없을 수는 있음
                board = makeBoardDTO(rs);
            }
        } catch (SQLException ex) {
            System.out.println("select error");
            throw ex;
        } finally {
            DBUtil.close(rs, ps, conn);
        }

        return board;
    }

    @Override
    public int updateReadCount(BoardDTO board, int count) throws SQLException {
        int result = 0;

        try {
            String sql = " UPDATE BOARD_TB SET READ_COUNT = " + count;
            sql += " WHERE NO = " + board.getNo();

            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(sql);
            result = ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("update error");
            throw ex;
        } finally {
            DBUtil.close(ps, conn);
        }

        return result;
    }

    private BoardDTO makeBoardDTO(ResultSet rs) throws SQLException {
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setNo(rs.getInt("NO"));
        boardDTO.setTitle(rs.getString("TITLE"));
        boardDTO.setWriter(rs.getInt("WRITER"));
        boardDTO.setContent(rs.getString("CONTENT"));
        boardDTO.setReadCount(rs.getInt("READ_COUNT"));
        boardDTO.setRegDate(rs.getString("REG_DATE"));
        return boardDTO;
    }
}
