package programmers.baemin.user.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import programmers.baemin.user.domain.User;
import programmers.baemin.user.domain.UserLoginDto;
import programmers.baemin.user.domain.UserRegisterDto;
import programmers.baemin.user.repository.UserRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

	private final UserRepository userRepository;

	public boolean register(UserRegisterDto registerDto) {
		User user = User.from(registerDto);
		userRepository.save(user);
		return true;
	}

	public boolean isDuplicateLoginId(String loginId) {
		String byLoginId = userRepository.findByLoginId(loginId);
		if (byLoginId == null) {
			return true;
		}
		return false;
	}

	public boolean login(UserLoginDto userLoginDto) {
		User findUser = userRepository.findByLoginIdAndPassword(userLoginDto.getLoginId(),
			userLoginDto.getPassword());
		if (findUser == null) {
			return false;
		}
		return true;
	}
}
