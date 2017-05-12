package test;

/**
 * Created by Smith on 2017/5/11.
 */
public class SequenceA implements Sequence {
    private static int number = 0;

    @Override
    public int getNumber() {
        return number++;
    }

    public static void main(String[] args) {
        SequenceA s = new SequenceA();
        Thread thread1 = new ClientThread(s);
        Thread thread2 = new ClientThread(s);
        Thread thread3 = new ClientThread(s);
        thread1.start();
        thread2.start();
        thread3.start();
    }
}
