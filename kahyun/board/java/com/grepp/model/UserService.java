package com.grepp.model;

import java.sql.SQLException;

public class UserService {
    private UserService(){
    }

    private static UserService instance =new UserService();
    public static UserService getInstance(){
        return instance;
    }
    UserRepository repo=UserRepositoryMysql.getInstance();

    public int login(UserDTO user) throws SQLException {
        return repo.Login(user);
    }
    public int signUp(UserDTO user) throws SQLException {
        return repo.signUp(user);
    }

    public String getUserName(String id)throws SQLException{
        return repo.getUserName(id);
    }

    public String getId(int no)throws SQLException{
        return repo.getId(no);
    }
}
