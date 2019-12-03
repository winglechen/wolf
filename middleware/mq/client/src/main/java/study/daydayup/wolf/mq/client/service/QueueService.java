package study.daydayup.wolf.mq.client.service;

import study.daydayup.wolf.framework.rpc.Result;
import study.daydayup.wolf.mq.client.entity.PubMessage;
import study.daydayup.wolf.mq.client.entity.Task;

/**
 * study.daydayup.wolf.mq.client.service
 *
 * @author Wingle
 * @since 2019/11/29 3:36 下午
 **/
public interface QueueService {
    Result<Task> sub(String topic, String consumer);
    Result<Task> sub(String topic, String consumer, String tags);
    Result ack(long taskId);
    Result fail(long taskId);
    Result pub(PubMessage message);
}
