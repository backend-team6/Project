package com.group.board.member.dto;

public class LoginDTO {
	private String loginId;
	private String password;

	public LoginDTO(String loginId, String password) {
		this.loginId = loginId;
		this.password = password;
	}

	public String getLoginId() {
		return loginId;
	}

	public String getPassword() {
		return password;
	}
}
