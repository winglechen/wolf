package study.daydayup.wolf.mq.client.agent;

import study.daydayup.wolf.mq.client.entity.Message;

/**
 * study.daydayup.wolf.mq.client
 *
 * @author Wingle
 * @since 2019/11/28 6:49 下午
 **/
public class MQ {
    private String group = "g-wolf";
    private long taskId = 0;

    MQ() { }

    MQ(String group) {
        this.group = group;
    }

    public void pub(Message message) {

    }

    public Message sub(String topic) {
        return sub(topic, "");
    }

    public Message sub(String topic, String tags) {
        this.taskId = 0;
        return new Message();
    }

    public void ack() {

    }

    public void cancel() {

    }

    public void fail() {

    }

    public void tryPub(Message message) {

    }

    public void confirm(String messageId) {

    }

    public void cancel(String messageId) {

    }

    public void delay(Message message, long seconds) {

    }

}
