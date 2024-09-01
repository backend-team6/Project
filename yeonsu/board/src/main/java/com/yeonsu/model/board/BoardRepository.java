package com.yeonsu.model.board;

import java.sql.SQLException;
import java.util.List;

//IBookManager
public interface BoardRepository {
    int insert(BoardDTO board, int writer) throws SQLException;
//    int update(BoardDTO board, String category) throws SQLException;
    int update(BoardDTO boardDTO) throws SQLException;
    int delete(int no) throws SQLException;
    List<BoardDTO> selectAll() throws SQLException;
    BoardDTO selectOne(int id) throws SQLException;

    int updateReadCount(BoardDTO boardDTO, int count) throws SQLException;
}
