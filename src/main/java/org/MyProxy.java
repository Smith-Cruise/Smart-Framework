package org;

import org.annotation.Aspect;
import org.annotation.Controller;
import org.proxy.Proxy;

/**
 * Created by Smith on 2017/5/9.
 */
@Aspect(Controller.class)
public class MyProxy extends Proxy {
    @Override
    protected void begin() {
        System.out.println("my proxy begin");
    }

    @Override
    protected void end() {
        System.out.println("my proxy end");
    }
}
