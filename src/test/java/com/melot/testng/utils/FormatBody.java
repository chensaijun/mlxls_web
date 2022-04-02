package com.melot.testng.utils;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import okhttp3.FormBody;

import java.util.Map;

/**
 * @author biaoge
 * @version 1.0.0
 * @date 2019/04/21
 */
public class FormatBody {

    /**
     * @param builder
     * @param bodyJson
     * @return post内body构造
     */
    public FormBody.Builder formatBody(FormBody.Builder builder, JsonObject bodyJson) {
        if (null == builder || null == bodyJson) {
            return builder;
        }
        String value = null;
        for (Map.Entry<String, JsonElement> entry : bodyJson.entrySet()) {
            value = entry.getValue().getAsString();
            builder.add(entry.getKey(), value);
        }
        return builder;
    }

}
