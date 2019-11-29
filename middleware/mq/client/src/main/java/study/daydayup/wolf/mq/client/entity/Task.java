package study.daydayup.wolf.mq.client.entity;

import lombok.Data;

/**
 * study.daydayup.wolf.mq.client.entity
 *
 * @author Wingle
 * @since 2019/11/29 3:38 下午
 **/
@Data
public class Task {
    private long taskId;
    private Message message;
}
