package study.daydayup.wolf.mq.client.service;

import study.daydayup.wolf.framework.rpc.Result;
import study.daydayup.wolf.mq.client.entity.PubMessage;

/**
 * study.daydayup.wolf.mq.client.service
 *
 * @author Wingle
 * @since 2019/11/29 3:34 下午
 **/
public interface MessageService {
    Result<String> pub(PubMessage message);
}
