package cglib;

/**
 * Created by Smith on 2017/5/12.
 */
public class First extends Proxy {
    @Override
    protected void start() {
        System.out.println("first start");
    }

    @Override
    protected void end() {
        System.out.println("first end");
    }
}
