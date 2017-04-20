package org.helper;

import org.annotation.Inject;
import org.util.ReflectionUtil;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by Smith on 2017/4/19.
 */
public class IocHelper {
    static {
        Map<Class<?>, Object> beanMap = BeanHelper.getBeanMap();
        for (Class<?> cls: beanMap.keySet()) {
            /*
            * 获取BEAN的类和实例
            * */
            Class<?> beanClass = cls;
            Object beanInstance = beanMap.get(beanClass);
            /*
            * 获取bean类的成员变量
            * */
            Field[] beanFields = beanClass.getDeclaredFields();
            for (Field beanField: beanFields) {
                /*
                * 判断是否有Inject注解
                * */
                if (beanField.isAnnotationPresent(Inject.class)) {
                    // 从BeanMap中获取数据，并且赋值
                    Class<?> beanFieldClass = beanField.getType();
                    Object beanFieldInstance = beanMap.get(beanFieldClass);
                    if (beanFieldInstance != null) {
                        ReflectionUtil.setField(beanInstance, beanField, beanFieldInstance);
                    }
                }
            }
        }
    }

}
