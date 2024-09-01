package com.grepp.model;

public class UserDTO {
    private String name;
    private String id;
    private String password;

    public UserDTO() {
    }

    public UserDTO(String id, String password) {
        this.id = id;
        this.password = password;
    }

    public UserDTO(String name, String id, String password) {
        this.name = name;
        this.id = id;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }
}
