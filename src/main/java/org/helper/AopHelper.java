package org.helper;

import org.annotation.Aspect;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.proxy.Proxy;
import org.proxy.ProxyManager;

import java.lang.annotation.Annotation;
import java.util.*;

/**
 * Created by Smith on 2017/4/23.
 */
public final class AopHelper {
    private static final Logger LOGGER = LogManager.getLogger();
    private static Map<Class<? extends Annotation>, Proxy> proxyMap = new HashMap<>();
    private static Map<Class<?>, Object> proxyBean = new HashMap<>();

    static {
        try {
            createProxyMap();
            doProxy();
            for (Map.Entry<Class<?>, Object> entry: proxyBean.entrySet()) {
                BeanHelper.updateBean(entry.getKey(), entry.getValue());
            }
        } catch (Exception e) {
            LOGGER.error("aop failure");
        }
    }

    private static Set<Class<?>> getAllProxy() {
        return ClassHelper.getAspectClassSet();
    }

    /*
    * key: Controller.class // Service.class
    * value: 各种代理 MyProxy...
    * */
    private static void createProxyMap() throws Exception {
        Set<Class<?>> proxySet = getAllProxy();
        for (Class<?> cls: proxySet) {
            Aspect aspect = cls.getAnnotation(Aspect.class);
            Class<? extends Annotation> target = aspect.value();
            Proxy proxy = (Proxy) cls.newInstance();
            proxyMap.put(target, proxy);
        }
    }

    private static void doProxy() {
        for (Map.Entry<Class<? extends Annotation>, Proxy> entry : proxyMap.entrySet()) {
            Set<Class<?>> classSet = ClassHelper.getClassSetByAnnotation(entry.getKey());
            for (Class<?> cls: classSet) {
                Object object = ProxyManager.getInstance(cls, entry.getValue());
                proxyBean.put(cls, object);
            }
        }
    }
}
