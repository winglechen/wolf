package study.daydayup.wolf.mq.client.service;

import study.daydayup.wolf.framework.rpc.Result;
import study.daydayup.wolf.mq.client.entity.Task;

/**
 * study.daydayup.wolf.mq.client.service
 *
 * @author Wingle
 * @since 2019/11/29 3:36 下午
 **/
public interface QueueService {
    Result<Task> sub(String topic);
    Result<Task> sub(String topic, String tags);
    Result ack(long taskId);
    Result fail(long taskId);
}
