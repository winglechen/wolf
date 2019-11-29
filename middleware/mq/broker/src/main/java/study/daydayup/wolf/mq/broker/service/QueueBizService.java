package study.daydayup.wolf.mq.broker.service;

import org.springframework.stereotype.Component;
import study.daydayup.wolf.mq.broker.dal.dataobject.MessageDO;
import study.daydayup.wolf.mq.broker.entity.Lock;
import study.daydayup.wolf.mq.client.entity.Task;

/**
 * study.daydayup.wolf.mq.broker.service
 *
 * @author Wingle
 * @since 2019/11/29 6:29 下午
 **/
@Component
public class QueueBizService {
    public Lock lock(String topic, String tags) {
        return new Lock();
    }

    public void unlock() {

    }

    public MessageDO getMessage(Lock lock) {
        return new MessageDO();
    }

    public Task createTask(MessageDO messageDO) {
        return new Task();
    }


}
