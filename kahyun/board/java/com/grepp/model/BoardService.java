package com.grepp.model;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class BoardService {
    private BoardService(){}
    private static BoardService instance=new BoardService();
    public static BoardService getInstance(){
        return instance;
    }

    private BoardRepository repo=BoardRepositoryMysql.getInstance();

    public List<BoardDTO> viewList() throws SQLException {
        return repo.selectAll();
    }

    public int write(BoardDTO board) throws SQLException {
        return repo.insert(board);
    }

    public BoardDTO viewOne(int no)throws SQLException{
        return repo.selectOne(no);
    }

    public int update(BoardDTO board) throws SQLException{
        return repo.update(board);
    }

    public int plusReadCount(int no)throws SQLException{
        return repo.pulsCount(no);
    }
}
