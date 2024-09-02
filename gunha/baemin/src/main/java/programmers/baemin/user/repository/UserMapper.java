package programmers.baemin.user.repository;

import org.apache.ibatis.annotations.Mapper;

import programmers.baemin.user.domain.User;

@Mapper
public interface UserMapper {
	public User findByLoginIdAndPassword(String loginId, String password);

	public void register(User user);

	public String findByLoginId(String loginId);
}
