package org.util;

import com.alibaba.fastjson.JSON;

/**
 * Created by Smith on 2017/4/20.
 */
public final class JsonUtil {
    public static String toJson(Object obj) {
        return JSON.toJSONString(obj);
    }
}
