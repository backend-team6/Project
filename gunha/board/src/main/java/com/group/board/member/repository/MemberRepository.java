package com.group.board.member.repository;

public interface MemberRepository {
	public String login(String loginId, String password);

	public void join(String loginId, String password);
}
