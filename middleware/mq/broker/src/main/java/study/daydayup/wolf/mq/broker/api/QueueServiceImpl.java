package study.daydayup.wolf.mq.broker.api;

import study.daydayup.wolf.framework.rpc.Result;
import study.daydayup.wolf.framework.rpc.RpcService;
import study.daydayup.wolf.mq.broker.dal.dataobject.MessageDO;
import study.daydayup.wolf.mq.broker.entity.Lock;
import study.daydayup.wolf.mq.broker.service.MessageBizService;
import study.daydayup.wolf.mq.broker.service.QueueBizService;
import study.daydayup.wolf.mq.broker.service.TaskBizService;
import study.daydayup.wolf.mq.client.entity.Message;
import study.daydayup.wolf.mq.client.entity.Task;
import study.daydayup.wolf.mq.client.exception.FailedLockException;
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
    @Resource
    private MessageBizService messageBizService;
    @Resource
    private TaskBizService taskBizService;

    @Override
    public Result<Task> sub(String topic, String consumer) {
        return sub(topic, consumer,"");
    }

    @Override
    public Result<Task> sub(String topic, String consumer, String tags) {
        Lock lock = bizService.lock(topic, consumer);

        Message message = messageBizService.findNextTopicMessage(lock.getTopic(), lock.getPartition(), lock.getOffset());
        if (null == message) {
            throw new FailedLockException();
        }

        Task task = taskBizService.createTask(consumer, message);
        bizService.unlock(lock, message.getId());

        return Result.ok(task);
    }

    @Override
    public Result ack(long taskId) {
        taskBizService.finishTask(taskId);
        return Result.ok();
    }

    @Override
    public Result fail(long taskId) {
        taskBizService.failTask(taskId);
        return Result.ok();
    }
}
