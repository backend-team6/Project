package com.yeonsu.model.user;

public class UserDTO {
    private int uno;
    private String loginId;
    private String loginPw;

    public UserDTO(int uno, String loginId, String loginPw) {
        this.uno = uno;
        this.loginId = loginId;
        this.loginPw = loginPw;
    }

    public UserDTO(String loginId, String loginPw) {
        this.loginId = loginId;
        this.loginPw = loginPw;
    }

    public int getUno() {
        return uno;
    }

    public void setUno(int uno) {
        this.uno = uno;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getLoginPw() {
        return loginPw;
    }

    public void setLoginPw(String loginPw) {
        this.loginPw = loginPw;
    }
}
