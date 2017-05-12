package cglib;

import net.sf.cglib.proxy.Enhancer;

/**
 * Created by Smith on 2017/5/12.
 */
public class Factory {
    public static Object getInstance(Class origin, Proxy proxy) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(origin);
        enhancer.setCallback(proxy);
        return enhancer.create();
    }
}
