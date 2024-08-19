package com.yeonsu.model.user;


import java.sql.SQLException;

public class UserService {
    private UserRepository repo = UserRepositoryMysql.getInstance();

    private UserService() {}
    private static UserService instance = new UserService();
    public static UserService getInstance() {
        return instance;
    }
    ///////////////////////////////////////////////////////

    public boolean login(String loginId, String loginPw) throws SQLException {
        UserDTO user = repo.selectOne(loginId);

        if (user == null) {
            return false;
        }

        return user.getLoginPw().equals(loginPw);
    }

    public boolean signup(String loginId, String loginPw) throws SQLException {
        return repo.insert(new UserDTO(loginId, loginPw)) == 1;
    }

    public UserDTO selectOne(int writer) throws SQLException {
        return repo.selectOneByUno(writer);
    }
}
