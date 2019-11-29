package study.daydayup.wolf.mq.client.entity;

import lombok.Data;

/**
 * study.daydayup.wolf.mq.client.entity
 *
 * @author Wingle
 * @since 2019/11/29 3:31 下午
 **/
@Data
public class PubMessage extends Message {
    private String producer;
}
