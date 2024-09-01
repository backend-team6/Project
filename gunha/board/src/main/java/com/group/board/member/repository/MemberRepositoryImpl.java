package com.group.board.member.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.group.board.exception.DuplicateLoginIdException;
import com.group.board.exception.InvalidLoginException;
import com.group.board.util.DBUtil;

public class MemberRepositoryImpl implements MemberRepository {
	private static MemberRepository memberRepository = new MemberRepositoryImpl();

	private MemberRepositoryImpl() {
	}

	public static MemberRepository getInstance() {
		return memberRepository;
	}

	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;

	@Override
	public String login(String loginId, String password) {
		try {
			con = DBUtil.getConnection();
			String sql = "select login_id from member where login_id = ? and password = ?";

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, loginId);
			pstmt.setString(2, password);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getString(1);
			}
			throw new InvalidLoginException("잘못된 아이디 또는 패스워드입니다.");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("SQL 오류");
		} finally {
			DBUtil.close(rs, pstmt, con);
		}
	}

	@Override
	public void join(String loginId, String password) {
		if (isNotDuplicateId(loginId)) {
			try {
				con = DBUtil.getConnection();
				String sql = "insert into member(login_id, password) values (?, ?);";

				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, loginId);
				pstmt.setString(2, password);

				int result = pstmt.executeUpdate();
				if (result < 0) {
					throw new InvalidLoginException("잘못된 아이디 또는 패스워드입니다.");
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException("SQL 오류");
			} finally {
				DBUtil.close(rs, pstmt, con);
			}
		}
	}

	private boolean isNotDuplicateId(String loginId) {
		try {
			con = DBUtil.getConnection();
			String sql = "select member_id from member where login_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, loginId);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				throw new DuplicateLoginIdException("중복된 아이디입니다.");
			}
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("SQL 오류");
		} finally {
			DBUtil.close(rs, pstmt, con);
		}
	}
}
