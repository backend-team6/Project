package com.group.board.board.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.group.board.board.domain.BoardDTO;
import com.group.board.board.dto.BoardWriteDTO;
import com.group.board.board.dto.UpdateDTO;
import com.group.board.exception.InvalidWriteException;
import com.group.board.util.DBUtil;

public class BoardRepositoryImpl implements BoardRepository {
	private static BoardRepository boardRepository = new BoardRepositoryImpl();

	private BoardRepositoryImpl() {
	}

	public static BoardRepository getInstance() {
		return boardRepository;
	}

	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;

	@Override
	public List<BoardDTO> findAll() {
		List<BoardDTO> boardList = new ArrayList<>();
		try {
			con = DBUtil.getConnection();
			String sql = "select no, writer, read_count, title, content, reg_date from board;";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				boardList.add(new BoardDTO(
					rs.getInt(1),
					rs.getString(2),
					rs.getInt(3),
					rs.getString(4),
					rs.getString(5),
					rs.getDate(6)
				));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("SQL 오류");
		} finally {
			DBUtil.close(rs, pstmt, con);
		}
		return boardList;
	}

	@Override
	public BoardDTO findByBoardNo(int no) {
		BoardDTO boardDTO = null;
		try {
			con = DBUtil.getConnection();
			String sql = "select no, writer, read_count, title, content, reg_date from board where no = ?;";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				boardDTO =  new BoardDTO(
					rs.getInt(1),
					rs.getString(2),
					rs.getInt(3),
					rs.getString(4),
					rs.getString(5),
					rs.getDate(6)
				);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("SQL 오류");
		} finally {
			DBUtil.close(rs, pstmt, con);
		}
		return boardDTO;
	}

	@Override
	public void write(BoardWriteDTO boardWriteDTO) {
		try {
			con = DBUtil.getConnection();
			String sql = "insert into board (writer, title, content, reg_date) values (?, ?, ?, ?);";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, boardWriteDTO.getWriter());
			pstmt.setString(2, boardWriteDTO.getTitle());
			pstmt.setString(3, boardWriteDTO.getContent());
			pstmt.setDate(4, boardWriteDTO.getRegDate());

			int result = pstmt.executeUpdate();
			if (result < 0) {
				throw new InvalidWriteException("잘못된 게시글 작성 접근입니다.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("SQL 오류");
		} finally {
			DBUtil.close(rs, pstmt, con);
		}
	}

	@Override
	public void update(UpdateDTO updateDTO) {
		try {
			con = DBUtil.getConnection();
			String sql = "update board set title = ?, content = ?, reg_date = ? where no = ?;";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, updateDTO.getTitle());
			pstmt.setString(2, updateDTO.getContent());
			pstmt.setDate(3, updateDTO.getRegDate());
			pstmt.setInt(4, updateDTO.getNo());

			int result = pstmt.executeUpdate();
			if (result < 0) {
				throw new InvalidWriteException("잘못된 게시글 작성 오류입니다.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("SQL 오류");
		} finally {
			DBUtil.close(pstmt, con);
		}
	}

	@Override
	public void incrementReadCount(int no) {
		try {
			con = DBUtil.getConnection();
			String sql = "update board set read_count = read_count + 1 where no = ?;";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("SQL 오류");
		} finally {
			DBUtil.close(pstmt, con);
		}
	}
}
