package programmers.baemin.user.domain;

import lombok.Data;

@Data
public class UserRegisterDto {
	private String loginId;

	private String password;

	public UserRegisterDto(String loginId, String password) {
		this.loginId = loginId;
		this.password = password;
	}
}
