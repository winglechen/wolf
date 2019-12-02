package study.daydayup.wolf.mq.broker.api;

import study.daydayup.wolf.framework.rpc.Result;
import study.daydayup.wolf.framework.rpc.RpcService;
import study.daydayup.wolf.mq.broker.dal.dataobject.MessageDO;
import study.daydayup.wolf.mq.broker.entity.Lock;
import study.daydayup.wolf.mq.broker.service.QueueBizService;
import study.daydayup.wolf.mq.client.entity.Task;
import study.daydayup.wolf.mq.client.service.QueueService;

import javax.annotation.Resource;

/**
 * study.daydayup.wolf.mq.broker.api
 *
 * @author Wingle
 * @since 2019/11/29 5:10 下午
 **/
@RpcService
public class QueueServiceImpl implements QueueService {
    @Resource
    private QueueBizService bizService;

    @Override
    public Result<Task> sub(String topic, String consumer) {
        return sub(topic, consumer,"");
    }

    @Override
    public Result<Task> sub(String topic, String consumer, String tags) {
        Lock lock = bizService.lock(topic, consumer);

        MessageDO messageDO = bizService.getMessage(lock);
        Task task = bizService.createTask(messageDO);

        bizService.unlock(lock, 1);
        return Result.ok(task);
    }

    @Override
    public Result ack(long taskId) {
        return null;
    }

    @Override
    public Result fail(long taskId) {
        return null;
    }
}
