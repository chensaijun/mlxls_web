package com.melot.testng.data.params;

/**
 * @Author: chensaijun
 * @Date: 2022/4/1
 **/

public class UserResultObject {
    private UserVoObject userVo;
    private String token;

    public UserVoObject getUserVo() {
        return userVo;
    }

    public void setUserVo(UserVoObject userVo) {
        this.userVo = userVo;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
