package com.group.board.member.service;

import com.group.board.member.dto.LoginDTO;
import com.group.board.member.repository.MemberRepository;
import com.group.board.member.repository.MemberRepositoryImpl;

public class MemberService {
	private static MemberService memberService = new MemberService();

	MemberRepository memberRepository;

	private MemberService() {
		this.memberRepository = MemberRepositoryImpl.getInstance();
	}

	public static MemberService getInstance() {
		return memberService;
	}

	public String login(LoginDTO loginDTO) {
		String loginId = loginDTO.getLoginId();
		String password = loginDTO.getPassword();
		String memberId = memberRepository.login(loginId, password);
		return memberId;
	}

	public void join(String loginId, String password) {
		memberRepository.join(loginId, password);
	}

}
