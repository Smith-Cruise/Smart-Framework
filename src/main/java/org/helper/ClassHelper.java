package org.helper;

import org.annotation.Controller;
import org.annotation.Service;
import org.config.ConfigHelper;
import org.util.ClassUtil;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Smith on 2017/4/17.
 */
public final class ClassHelper {
    private static final Set<Class<?>> CLASS_SET;

    static {
        String basePackage = ConfigHelper.getAppBasePackage();
        CLASS_SET = ClassUtil.getClassSet(basePackage);
    }

    public static Set<Class<?>> getClassSet() {
        return CLASS_SET;
    }

    /*
    *  获取Service
    * */
    public static Set<Class<?>> getServiceClassSet() {
        Set<Class<?>> classSet = new HashSet<>();
        for (Class<?> c: CLASS_SET) {
            if (c.isAnnotationPresent(Service.class)) {
                classSet.add(c);
            }
        }
        return classSet;
    }

    public static Set<Class<?>> getControllerClassSet() {
        Set<Class<?>> classSet = new HashSet<>();
        for (Class<?> c: CLASS_SET) {
            if (c.isAnnotationPresent(Controller.class)) {
                classSet.add(c);
            }
        }
        return classSet;
    }

    public static Set<Class<?>> getBeanClassSet() {
        Set<Class<?>> classSet = new HashSet<>();
        classSet.addAll(getControllerClassSet());
        classSet.addAll(getServiceClassSet());
        return classSet;
    }
}
