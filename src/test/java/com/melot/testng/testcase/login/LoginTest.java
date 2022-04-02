package com.melot.testng.testcase.login;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import com.melot.testng.constants.Constants;
import com.melot.testng.utils.FormatHeader;
import com.melot.testng.utils.PasswordFunctions;
import com.melot.testng.utils.SecurityFunctions;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * @Author: chensaijun
 * @Date: 2022/3/29
 **/

public class LoginTest {
    @Test
    public void testlogin() throws IOException {
        String url = Constants.BASE_URL + "/webapi/login/superadmin";

        //获取code
        JSONObject codeJson = JSONObject.parseObject(GetImage.getimage());
        JSONObject codeJsonData = JSONObject.parseObject(codeJson.get("object").toString());
        String code = codeJsonData.getString("content");

        //密码加密
        String encodePsw = null;
        String password = "123456";
        try {
            encodePsw = PasswordFunctions.encodePassword(password);

        }catch (Exception e){
            e.printStackTrace();
        }
        //拼接get参数
        Request.Builder requestBuilder = new Request.Builder();
        HttpUrl.Builder urlBuilder = HttpUrl.parse(url).newBuilder();
        urlBuilder.addQueryParameter("username","miluo");
        urlBuilder.addQueryParameter("password",encodePsw);
        urlBuilder.addQueryParameter("code",code);
        urlBuilder.addQueryParameter("p","1");
        requestBuilder.url(urlBuilder.build());

        //拼接Header
        JsonObject headerJson = Constants.getDefaultHeader();
        String sv = SecurityFunctions.getSingedValue(headerJson);
        headerJson.addProperty("Content-Type", Constants.CONTENT_TYPE_URLENCODED);
        headerJson.addProperty("s",sv);

        OkHttpClient client = new OkHttpClient();

        FormatHeader formatHeader = new FormatHeader();
        requestBuilder = formatHeader.formatHeader(requestBuilder,headerJson);

        Request request = requestBuilder.build();

        //发送client请求
        Response response = client.newCall(request).execute();
        String body = response.body().string();
        System.out.println(body);



        //断言
        JSONObject result = JSONObject.parseObject(body);
        String flag = result.getString("success");
        System.out.println(flag);
        Boolean result1 = Boolean.parseBoolean(flag);
        System.out.println("断言结果:"+result1);
        Assert.assertEquals(result1,true);
    }

}
