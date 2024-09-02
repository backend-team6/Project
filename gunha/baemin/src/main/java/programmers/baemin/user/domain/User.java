package programmers.baemin.user.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {
	private int id;

	private String loginId;

	private String password;

	public User(int id, String loginId, String password) {
		this.id = id;
		this.loginId = loginId;
		this.password = password;
	}

	public static User from(UserRegisterDto userRegisterDto) {
		return User.builder()
			.loginId(userRegisterDto.getLoginId())
			.password(userRegisterDto.getPassword())
			.build();
	}

	public static User from(UserLoginDto userLoginDto) {
		return User.builder()
			.loginId(userLoginDto.getLoginId())
			.password(userLoginDto.getPassword())
			.build();
	}
}
