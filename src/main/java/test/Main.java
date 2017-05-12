package test;

/**
 * Created by Smith on 2017/5/11.
 */
public class Main {
    public static void main(String[] args) {
        One one = new One();
        System.out.println(one.getClass().isAssignableFrom(Base.class));
        Base base = new Base();
        System.out.println(base.getClass().isAssignableFrom(One.class));
    }
}
