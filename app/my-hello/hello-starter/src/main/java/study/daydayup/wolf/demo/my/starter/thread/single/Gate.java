package study.daydayup.wolf.demo.my.starter.thread.single;

import lombok.Data;

/**
 * study.daydayup.wolf.demo.my.starter.thread.single
 *
 * @author Wingle
 * @since 2020/4/1 10:25 下午
 **/
@Data
public class Gate {
    private int counter = 0;
    private String name = "Nobody";
    private String address = "NoWhere";

    public synchronized void pass(String name, String address) {
        this.name = name;
        this.address = address;
        this.counter++;
        check();

        System.out.println(toString() + " passed away .........");
    }

    @Override
    public String toString() {
        return "No." + counter + ":" + name + ", " + address;
    }

    private void check() {
        if (name.charAt(0) != address.charAt(0)) {
            System.out.println("******* BROKEN ******* " + toString());
        }
    }
}
