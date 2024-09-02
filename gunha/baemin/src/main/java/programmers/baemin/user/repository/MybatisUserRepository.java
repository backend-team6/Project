package programmers.baemin.user.repository;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import programmers.baemin.user.domain.User;

@Slf4j
@RequiredArgsConstructor
@Repository
public class MybatisUserRepository implements UserRepository {

	private final UserMapper userMapper;

	@Override
	public User findByLoginIdAndPassword(String loginId, String password) {
		log.info("[MybatisUserRepository] findByLoginIdAndPassword ({}, {})", loginId, password);
		return userMapper.findByLoginIdAndPassword(loginId, password);
	}

	@Override
	public User save(User user) {
		userMapper.register(user);
		return user;
	}

	@Override
	public String findByLoginId(String loginId) {
		return userMapper.findByLoginId(loginId);
	}

}
