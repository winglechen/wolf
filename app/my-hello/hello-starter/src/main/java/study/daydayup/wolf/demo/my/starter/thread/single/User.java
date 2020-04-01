package study.daydayup.wolf.demo.my.starter.thread.single;

/**
 * study.daydayup.wolf.demo.my.starter.thread.single
 *
 * @author Wingle
 * @since 2020/4/1 10:26 下午
 **/
public class User extends Thread {
    private final Gate gate;
    private final String userName;
    private final String userAddress;

    public User(Gate gate, String name, String address) {
        this.gate = gate;
        userName = name;
        userAddress = address;
    }

    public void run() {
        System.out.println(userName + " Begin ...");
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            gate.pass(userName, userAddress);
        }
    }
}
