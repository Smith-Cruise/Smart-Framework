package test;

/**
 * Created by Smith on 2017/5/11.
 */
public class SequenceB implements Sequence {
    private static ThreadLocal<Integer> numberContainer = new ThreadLocal<Integer>() {

    };

    @Override
    public int getNumber() {
        return 0;
    }
}
