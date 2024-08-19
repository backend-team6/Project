package com.grepp.model;

import java.sql.SQLException;
import java.util.List;

public interface BoardRepository {
    List<BoardDTO> selectAll() throws SQLException;
    BoardDTO selectOne(int no)throws SQLException;
    int insert(BoardDTO board)throws SQLException;
    int update(BoardDTO boardDTO)throws SQLException;
    int pulsCount(int no)throws SQLException;
}
