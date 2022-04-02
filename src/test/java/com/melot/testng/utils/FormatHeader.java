package com.melot.testng.utils;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import okhttp3.Request;

import java.util.Map;

/**
 * @author biaoge
 * @version 1.0.0
 * @date 2019/04/21
 */
public class FormatHeader {

    /**
     * @return header JsonObject 构造至 header
     */
    public Request.Builder formatHeader(Request.Builder builder, JsonObject headerJson) {
        if (null == builder || null == headerJson) {
            return builder;
        }
        String value = null;
        for (Map.Entry<String, JsonElement> entry : headerJson.entrySet()) {
            value = entry.getValue().getAsString();
            builder.addHeader(entry.getKey(), value);
        }
        return builder;
    }

}
