package study.daydayup.wolf.mq.broker.service;


import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import study.daydayup.wolf.mq.broker.dal.dao.MessageDAO;
import study.daydayup.wolf.mq.broker.dal.dataobject.MessageDO;
import study.daydayup.wolf.mq.client.entity.Message;

import javax.annotation.Resource;

/**
 * study.daydayup.wolf.mq.broker.service
 *
 * @author Wingle
 * @since 2019/12/3 4:50 下午
 **/
@Component
public class MessageBizService {
    @Resource
    private MessageDAO messageDAO;

    public Message findNextTopicMessage(String topic, byte partition, int offset) {
        MessageDO messageDO = messageDAO.selectNextTopicMessage(topic, partition, offset);
        if (null == messageDO) {
            return null;
        }

        Message message = new Message();
        BeanUtils.copyProperties(messageDO, message);

        return message;
    }
}
