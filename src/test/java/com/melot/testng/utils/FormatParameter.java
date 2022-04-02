package com.melot.testng.utils;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import okhttp3.HttpUrl;

import java.util.Map;

/**
 * @author biaoge
 * @version 1.0.0
 * @date 2019/04/20
 */
public class FormatParameter {

    public HttpUrl.Builder formatParameter(HttpUrl.Builder urlBuilder, JsonObject paramJson) {
        if (null == urlBuilder || null == paramJson) {
            return urlBuilder;
        }
        String value = null;
        for (Map.Entry<String, JsonElement> entry : paramJson.entrySet()) {
            value = entry.getValue().getAsString();
            urlBuilder.addQueryParameter(entry.getKey(), value);
        }
        return urlBuilder;
    }

}
