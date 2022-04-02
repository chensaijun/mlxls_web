package com.melot.testng.data.params;

/**
 * @Author: chensaijun
 * @Date: 2022/4/1
 **/

public class LoginParserBean {
    private  boolean success;
    private String msg;
    private String errorCode;
    private UserResultObject object;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public UserResultObject getObject() {
        return object;
    }

    public void setObject(UserResultObject object) {
        this.object = object;
    }
}
