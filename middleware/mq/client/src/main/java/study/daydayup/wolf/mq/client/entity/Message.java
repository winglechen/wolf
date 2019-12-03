package study.daydayup.wolf.mq.client.entity;

import lombok.Data;

/**
 * study.daydayup.wolf.mq.client.message
 *
 * @author Wingle
 * @since 2019/11/28 6:52 下午
 **/
@Data
public class Message {
    private int id;
    private String topic;
    private Byte partition;

    private String messageId;
    private String tags;
    private String message;
}
