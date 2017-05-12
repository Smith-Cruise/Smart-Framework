package org.helper;

import org.annotation.Aspect;
import org.annotation.Controller;
import org.annotation.Service;
import org.config.ConfigHelper;
import org.util.ClassUtil;

import java.lang.annotation.Annotation;
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
    * 获取某父类的所有子类，不包括自己
    * */
    public static Set<Class<?>> getClassSetBySuper(Class<?> superClass) {
        Set<Class<?>> classSet = new HashSet<>();
        for (Class<?> cls: CLASS_SET) {
            if (superClass.isAssignableFrom(cls) & superClass!= cls) {
                classSet.add(cls);
            }
        }
        return classSet;
    }


    /*
    *  获取Service
    * */
    public static Set<Class<?>> getServiceClassSet() {
        return getClassSetByAnnotation(Service.class);
    }

    /*
    * 获取Aspect
    * */
    public static Set<Class<?>> getAspectClassSet() {
        return getClassSetByAnnotation(Aspect.class);
    }

    /*
    * 获取Controller
    * */
    public static Set<Class<?>> getControllerClassSet() {
        return getClassSetByAnnotation(Controller.class);
    }

    public static Set<Class<?>> getBeanClassSet() {
        // todo
        Set<Class<?>> classSet = new HashSet<>();
        classSet.addAll(getControllerClassSet());
        classSet.addAll(getServiceClassSet());
        return classSet;
    }

    /*
    * 获取所有带某注解的类
    * */
    public static Set<Class<?>> getClassSetByAnnotation(Class<? extends Annotation> annotationClass) {
        Set<Class<?>> classSet = new HashSet<>();
        for (Class<?> cls: CLASS_SET) {
            if (cls.isAnnotationPresent(annotationClass)) {
                classSet.add(cls);
            }
        }
        return classSet;
    }
}
