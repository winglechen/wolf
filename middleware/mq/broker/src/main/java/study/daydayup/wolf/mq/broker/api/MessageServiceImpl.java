package study.daydayup.wolf.mq.broker.api;

import org.springframework.beans.BeanUtils;
import study.daydayup.wolf.framework.rpc.Result;
import study.daydayup.wolf.framework.rpc.RpcService;
import study.daydayup.wolf.mq.broker.dal.dao.MessageDAO;
import study.daydayup.wolf.mq.broker.dal.dataobject.MessageDO;
import study.daydayup.wolf.mq.client.entity.PubMessage;
import study.daydayup.wolf.mq.client.service.MessageService;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * study.daydayup.wolf.mq.broker.api
 *
 * @author Wingle
 * @since 2019/11/29 5:08 下午
 **/
@RpcService
public class MessageServiceImpl implements MessageService {
    @Resource
    private MessageDAO messageDAO;

    @Override
    public Result pub(PubMessage message) {
        MessageDO messageDO = new MessageDO();
        BeanUtils.copyProperties(message, messageDO);

        Date now = new Date();
        messageDO.setCreatedAt(now);

        messageDAO.insertSelective(messageDO);
        return Result.ok();
    }
}
