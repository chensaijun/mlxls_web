package com.melot.testng.testcase.login;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.melot.testng.constants.Constants;
import com.melot.testng.utils.FormatHeader;
import com.melot.testng.utils.SecurityFunctions;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

/**
 * @Author: chensaijun
 * @Date: 2022/3/31
 **/

public class GetImage {
    private static Object Response;
    private Gson gson = new Gson();

    public static String getimage() throws IOException {

        String url = Constants.BASE_URL + "/webapi/image/code"+"?"+"p=1";
        Request.Builder requestBuilder = new Request.Builder();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(url).newBuilder();
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
        String jsonString = response.body().string();

        return jsonString;
    }
}
