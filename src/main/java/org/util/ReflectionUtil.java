package org.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by Smith on 2017/4/17.
 */
public final class ReflectionUtil {
    private static final Logger LOGGER = LogManager.getLogger();

    /*
    *  创建实例
    * */
    public static Object newInstance(Class<?> cls) {
        Object instance;
        try {
            instance = cls.newInstance();
        } catch (Exception e) {
            LOGGER.error("New instance failure");
            throw new RuntimeException(e);
        }
        return instance;
    }


    /*
    *  调用方法
    * */
    public static Object invokeMethod(Object obj, Method method, Object... args) {
        Object result;
        try {
            method.setAccessible(true);
            result = method.invoke(obj, args);
        } catch (Exception e) {
            LOGGER.error("invoke method failure");
            throw new RuntimeException(e);
        }
        return result;
    }

    /*
    * 设置成员变量的值
    * */
    public static void setField(Object obj, Field field, Object value) {
        try {
            field.setAccessible(true);
            field.set(obj, value);
        } catch (Exception e) {
            LOGGER.error("set field failure");
            throw new RuntimeException(e);
        }
    }
}
