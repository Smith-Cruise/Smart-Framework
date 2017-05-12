package org.proxy;

import net.sf.cglib.proxy.Enhancer;

import java.util.List;

/**
 * Created by Smith on 2017/5/12.
 */
public class ProxyManager {
    private static Enhancer enhancer = new Enhancer();

    public static Object getInstance(Class targetClass, Proxy proxy) {
        enhancer.setSuperclass(targetClass);
        enhancer.setCallback(proxy);
        return enhancer.create();
    }
}
