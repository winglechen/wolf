package study.daydayup.wolf.mq.client.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * study.daydayup.wolf.mq.client.message
 *
 * @author Wingle
 * @since 2019/11/28 6:52 下午
 **/
@Data
public class Message implements Serializable {
    private int id;
    private String topic;
    private byte shard;

    private String messageId;
    private String tags;
    private String message;
}
