package programmers.baemin.user.repository;

import programmers.baemin.user.domain.User;

public interface UserRepository {
	public User findByLoginIdAndPassword(String loginId, String password);

	public User save(User user);

	public String findByLoginId(String loginId);
}
