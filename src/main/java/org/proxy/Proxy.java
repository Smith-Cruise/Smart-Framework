package org.proxy;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by Smith on 2017/4/21.
 */
public abstract class Proxy implements MethodInterceptor {
    @Override
    public final Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        begin();
        Object obj = methodProxy.invokeSuper(o, objects);
        end();
        return obj;
    }

    protected void begin() {

    }

    protected void end() {

    }
}
