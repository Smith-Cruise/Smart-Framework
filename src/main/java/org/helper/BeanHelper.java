package org.helper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.util.ReflectionUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by Smith on 2017/4/17.
 */
public final class BeanHelper {
    /*
    * 存放Bean类与Bean实例的MAP
    * */
    private static final Map<Class<?>, Object> BEAN_MAP = new HashMap<>();

    private static final Logger LOGGER = LogManager.getLogger();

    static {
        Set<Class<?>> beanClassSet = ClassHelper.getBeanClassSet();
        for (Class<?> beanClass: beanClassSet) {
            Object obj = ReflectionUtil.newInstance(beanClass);
            BEAN_MAP.put(beanClass, obj);
        }
    }

    public static Map<Class<?>, Object> getBeanMap() {
        return BEAN_MAP;
    }

    @SuppressWarnings("unchecked")
    public static <T> T getBean(Class<T> cls) {
        if (!BEAN_MAP.containsKey(cls)) {
            LOGGER.error("Can't get bean by class");
            throw new RuntimeException("Can't get bean by class");
        }
        return (T) BEAN_MAP.get(cls);
    }


}
