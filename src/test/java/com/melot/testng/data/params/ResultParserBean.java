package com.melot.testng.data.params;

/**
 * @Author: chensaijun
 * @Date: 2022/4/1
 **/

public class ResultParserBean {
    private boolean success;
    private String errorCode;
    private String msg;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
