package cglib;

/**
 * Created by Smith on 2017/5/12.
 */
public class Second extends Proxy {
    @Override
    protected void start() {
        System.out.println("second start");
    }

    @Override
    protected void end() {
        System.out.println("second end");
    }
}
