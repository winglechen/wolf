package study.daydayup.wolf.mq.client;

import study.daydayup.wolf.mq.client.entity.Message;

/**
 * study.daydayup.wolf.mq.client
 *
 * @author Wingle
 * @since 2019/11/28 6:49 下午
 **/
public class MQ {
    private String group;
    private static final String defaultGroup = "g-wolf";

    MQ() {
        this(MQ.defaultGroup);
    }

    MQ(String group) {
        this.group = group;
    }

    public void pub(Message message) {

    }

    public Message sub(String topic) {
        return new Message();
    }

    public void ack(Message message) {

    }

    public void tryPub(Message message) {

    }

    public void confirm(String messageId) {

    }

    public void cancel(String messageId) {

    }

}
