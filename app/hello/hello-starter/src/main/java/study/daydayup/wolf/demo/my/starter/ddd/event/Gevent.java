package study.daydayup.wolf.demo.my.starter.ddd.event;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import lombok.Data;

/**
 * study.daydayup.wolf.demo.my.starter.ddd.event
 *
 * @author Wingle
 * @since 2019/12/16 9:35 下午
 **/
public class Gevent {
    public static void main(String[] args) {
        EventBus eventBus = new EventBus("test");
        EventListener listener = new EventListener();

        eventBus.register(listener);

        eventBus.post(new TestEvent("abc"));
        eventBus.post(new TestEvent("bcd"));
        eventBus.post(new TestEvent("wingle"));
    }

    @Data
    static class TestEvent {
        private String message;
        public TestEvent(String message) {
            this.message = message;
        }
    }

    @Data
    static class EventListener {
        private String lastMessage;

        @Subscribe
        public void listen(TestEvent event) {
            lastMessage = event.getMessage();

            System.out.println("event class name:" + event.getClass().getSimpleName());

            System.out.println("get Message:" + lastMessage);
        }
    }
}
