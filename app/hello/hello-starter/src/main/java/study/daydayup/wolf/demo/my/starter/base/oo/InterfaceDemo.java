package study.daydayup.wolf.demo.my.starter.base.oo;

import lombok.Data;
import study.daydayup.wolf.common.sm.Event;

import java.util.ArrayList;
import java.util.List;

/**
 * study.daydayup.wolf.demo.my.starter.base.oo
 *
 * @author Wingle
 * @since 2019/12/26 5:50 下午
 **/
public class InterfaceDemo {
    public static void main(String[] args) {
        List<Event> eventList = new ArrayList<>();

        eventList.add(new PaidEvent());
        eventList.add(new SendEvent());

        PaidEvent paidEvent = (PaidEvent) eventList.get(0);
        System.out.println("paidEvent: " + paidEvent.getName());
    }

    @Data
    static class PaidEvent implements Event {
        private String name = "paid";
    }

    @Data
    static class SendEvent implements Event {
        private String name = "send";
    }
}
