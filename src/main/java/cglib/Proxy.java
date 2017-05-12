package cglib;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by Smith on 2017/5/12.
 */
public abstract class Proxy implements MethodInterceptor {
    protected void start() {}

    protected void end() {}

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        start();
        Object object = methodProxy.invokeSuper(o, objects);
        end();
        return object;
    }
}
