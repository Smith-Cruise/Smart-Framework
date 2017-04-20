package org.bean;

import java.util.Map;

/**
 * Created by Smith on 2017/4/20.
 */
public class Param {
    private Map<String, Object> paramMap;

    public Param(Map<String, Object> paramMap) {
        this.paramMap = paramMap;
    }

    public Map<String, Object> getMap() {
        return paramMap;
    }
}
