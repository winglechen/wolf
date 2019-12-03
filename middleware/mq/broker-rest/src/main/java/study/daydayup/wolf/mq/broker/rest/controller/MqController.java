package study.daydayup.wolf.mq.broker.rest.controller;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import study.daydayup.wolf.mq.client.entity.PubMessage;
import study.daydayup.wolf.mq.client.entity.Task;
import study.daydayup.wolf.mq.client.service.QueueService;

import java.util.UUID;

/**
 * study.daydayup.wolf.mq.broker.rest.controller
 *
 * @author Wingle
 * @since 2019/12/3 8:46 下午
 **/
@RestController
public class MqController {
    @Reference
    private QueueService queue;

    @RequestMapping("/pub")
    public String pub() {
        PubMessage message = new PubMessage();
        message.setProducer("wolf-producer");
        message.setTopic("wolf-topic");

        String uuid = UUID.randomUUID().toString().replace("-", "").toLowerCase();
        message.setMessageId(uuid);
        message.setMessage("wolf-message");
        message.setShard((byte)1);
        message.setTags("test");

        queue.pub(message);

        return "hello mq";
    }

    public Task sub() {
        Task task = new Task();

        return task;
    }
}
