package study.daydayup.wolf.demo.my.starter.thread.single;

/**
 * study.daydayup.wolf.demo.my.starter.thread.single
 *
 * @author Wingle
 * @since 2020/4/1 10:25 下午
 **/
public class Main {
    public static void main(String[] args) {
        System.out.println("Testing gate, hit CTRL+C to exit.");

        Gate gate = new Gate();

        new User(gate, "AUser", "AAddress").start();
        new User(gate, "BUser", "BAddress").start();
        new User(gate, "CUser", "CAddress").start();
    }
}
