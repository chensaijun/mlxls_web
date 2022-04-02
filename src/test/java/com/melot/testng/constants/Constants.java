package com.melot.testng.constants;

import com.google.gson.JsonObject;
import com.melot.testng.utils.SecurityFunctions;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * @Author: chensaijun
 * @Date: 2022/3/29
 **/

public class Constants {
    public static final String BASE_URL = "http://10.4.24.93:10010";
    public static final String CONTENT_TYPE_JSON = "application/json";
    public static final String CONTENT_TYPE_URLENCODED = "application/x-www-form-urlencoded";
    public static JsonObject headerJson = null;


    /**
     * @return 返回默认heaser json
     */
    public static JsonObject getDefaultHeader() {
        headerJson = new JsonObject();
        headerJson.addProperty("p", "1");
        headerJson.addProperty("a", "1");
        headerJson.addProperty("c", "-1");
        headerJson.addProperty("v", "0.0.1");
        headerJson.addProperty("t", Calendar.getInstance().getTimeInMillis());
        return headerJson;
    }

    /**
     * @return 帐号
     */
    public static String getName() {
        String username = null;
        if (isReaseEnviroment()) {
            username = "admin";
        } else {
            username = "miluo";
        }

        return username;
    }

    /**
     * @return 密码
     */
    public static String getPassword() {
        String password = null;
        if (isReaseEnviroment()) {
            password = "123456";
        } else {
            password = "123456";
        }
        return password;
    }

    /**
     * @return 平台号
     */
    public static String getP() {
        String p = "1";
        return p;

    }

    /**
     * @return true 为正式环境
     */
    private static boolean isReaseEnviroment() {
//        return true;
        return false;

    }

    /**
     * @return 返回接口请求url
     */
    public static String getBaseUrl() {
        String baseUrl = null;
        if (isReaseEnviroment()) {
            baseUrl = "https://api.retail.melot.cn";
        } else {
            baseUrl = "http://10.4.24.93:10010";
        }
        return baseUrl;
    }

}
