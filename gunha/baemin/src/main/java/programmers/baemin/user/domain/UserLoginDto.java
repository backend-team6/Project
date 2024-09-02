package programmers.baemin.user.domain;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UserLoginDto {
	private String loginId;
	private String password;
	private String redirectUrl;

	public UserLoginDto(String loginId, String password, String redirectUrl) {
		this.loginId = loginId;
		this.password = password;
		this.redirectUrl = redirectUrl;
	}
}
