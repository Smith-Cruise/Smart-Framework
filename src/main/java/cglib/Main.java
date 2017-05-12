package cglib;

/**
 * Created by Smith on 2017/5/12.
 */
public class Main {
    public static void main(String[] args) {
        Proxy first = new First();
        Proxy second = new Second();
        Object object = Factory.getInstance(Origin.class, first);
        object = Factory.getInstance(object.getClass(), second);
        Origin origin = (Origin) object;
        origin.say();
    }
}
