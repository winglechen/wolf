package study.daydayup.wolf.mq.broker.service;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import study.daydayup.wolf.mq.broker.dal.dao.QueueDAO;
import study.daydayup.wolf.mq.broker.dal.dataobject.MessageDO;
import study.daydayup.wolf.mq.broker.dal.dataobject.QueueDO;
import study.daydayup.wolf.mq.broker.entity.Lock;
import study.daydayup.wolf.mq.client.entity.Task;
import study.daydayup.wolf.mq.client.exception.FailedLockException;

import javax.annotation.Resource;
import java.util.Date;

/**
 * study.daydayup.wolf.mq.broker.service
 *
 * @author Wingle
 * @since 2019/11/29 6:29 下午
 **/
@Component
public class QueueBizService {
    @Resource
    private QueueDAO queueDAO;

    @Transactional
    public Lock lock(String topic, String consumer) {
        Lock lock = new Lock();

        QueueDO queueDO = queueDAO.lockByConsumer(topic, consumer);
        if (null == queueDO) {
            throw new FailedLockException();
        }

        BeanUtils.copyProperties(queueDO, lock);
        queueDAO.updateConsumerLock(lock.getId(), new Date());

        return lock;
    }

    public void unlock(Lock lock, int newOffset) {
        int id = lock.getId();

        queueDAO.unlockByConsumer(id, newOffset);
    }

    public MessageDO getMessage(Lock lock) {
        return new MessageDO();
    }

    public Task createTask(MessageDO messageDO) {
        return new Task();
    }


}
