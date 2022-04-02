package com.melot.testng.utils;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.melot.testng.constants.Constants;
import com.melot.testng.data.params.LoginParserBean;
import com.melot.testng.testcase.login.GetImage;
import okhttp3.*;

import java.io.IOException;

/**
 * @Author: chensaijun
 * @Date: 2022/4/1
 **/

public class LoginUtils {
    private static Gson gson = new Gson();


    public static LoginParserBean loginResquest(String username, String password, String p) throws IOException {
        String url = Constants.getBaseUrl()+ "/webapi/login/superadmin";

        //获取code
        JSONObject codeJson = JSONObject.parseObject(GetImage.getimage());
        JSONObject codeJsonData = JSONObject.parseObject(codeJson.get("object").toString());
        String code = codeJsonData.getString("content");

        //密码加密
        String encodePsw = null;
        try {
            encodePsw = PasswordFunctions.encodePassword(password);

        } catch (Exception e) {
            e.printStackTrace();
        }
        //拼接get参数
        Request.Builder requestBuilder = new Request.Builder();
        HttpUrl.Builder urlBuilder = HttpUrl.parse(url).newBuilder();
        //获取帐号、密码、平台号
        urlBuilder.addQueryParameter("username", username);
        urlBuilder.addQueryParameter("password", encodePsw);
        urlBuilder.addQueryParameter("p", p);
        requestBuilder.url(urlBuilder.build());

        //拼接Header
        JsonObject headerJson = Constants.getDefaultHeader();
        String sv = SecurityFunctions.getSingedValue(headerJson);
        headerJson.addProperty("Content-Type", Constants.CONTENT_TYPE_URLENCODED);
        headerJson.addProperty("s", sv);

        OkHttpClient client = new OkHttpClient();

        FormatHeader formatHeader = new FormatHeader();
        requestBuilder = formatHeader.formatHeader(requestBuilder, headerJson);

        Request request = requestBuilder.build();

        //发送client请求
        Response response = client.newCall(request).execute();
        String body = response.body().string();
        System.out.println(body);

        //json字符串转换为LoginParserBean.class类型
        LoginParserBean loginParserBean = gson.fromJson(body, LoginParserBean.class);
        //获取id和token设置为系统变量
        if (loginParserBean.isSuccess()){
            String userId = loginParserBean.getObject().getUserVo().getId();
            String token = loginParserBean.getObject().getToken();
            System.out.println(userId);
            System.out.println(token);
            System.setProperty("user_id", userId);
            System.setProperty("user_token",token);
        }else {
            System.out.println("error msg=" + loginParserBean.getMsg() + ",error code=" + loginParserBean.getErrorCode());
        }
        return loginParserBean;
    }
}
