package com.wolf.framework.middleware.mq.admin;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;
import com.wolf.common.util.lang.JSONUtil;
import com.wolf.common.util.lang.StringUtil;
import com.wolf.framework.layer.web.Controller;
import com.wolf.framework.middleware.mq.admin.command.ConsumerRestartCommand;
import com.wolf.framework.middleware.mq.admin.command.ConsumerResumeCommand;
import com.wolf.framework.middleware.mq.admin.command.ConsumerSuspendCommand;
import com.wolf.framework.middleware.mq.annotation.MQConsumer;
import com.wolf.framework.middleware.mq.config.MQConfig;
import com.wolf.framework.middleware.mq.core.MQVendor;
import com.wolf.framework.middleware.mq.core.consumer.Consumer;
import com.wolf.framework.middleware.mq.core.consumer.ConsumerManager;
import com.wolf.framework.middleware.mq.core.producer.ProducerManager;
import com.wolf.framework.middleware.mq.core.transaction.TransactionProducerManager;
import com.wolf.framework.middleware.mq.starter.MQStarter;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author weixing
 * @since 2022/10/11 20:42
 */
@RestController
public class ConsumerAdminController implements Controller {
    @Resource
    private MQStarter mqStarter;
    @Resource
    private MQConfig mqConfig;

    @GetMapping(value = "/wolf/mq/status", produces = "application/json")
    public String status() {
        ProducerManager producerManager = mqStarter.getProducerManager();
        TransactionProducerManager transactionProducerManager = mqStarter.getTransactionProducerManager();
        ConsumerManager consumerManager = mqStarter.getConsumerManager();

        Map<String, Object> status = new HashMap<>();

        // config
        Map<String, Object> config = new HashMap<>();
        config.put("vendors", mqConfig.getVendors());
        config.put("consumerGroups", mqConfig.getConsumerGroups());
        config.put("transactions", mqConfig.getTransactions());

        status.put("config", config);

        // producers
        status.put("producers", producerManager.getVendorProducers().entrySet().stream().collect(
            Collectors.toMap(Map.Entry::getKey, e -> {
                return e.getValue()
                    .stream()
                    .map(MQVendor::getVendorInstanceName)
                    .collect(
                        Collectors.toList()
                    );
            })
        ));
        status.put("transactionProducers", transactionProducerManager.getVendorProducers());
        status.put("consumers", consumerManager.getConsumers().entrySet().stream().collect(
            Collectors.toMap(Map.Entry::getKey, e -> {
                Map<String, String> m = new HashMap<>();
                MQConsumer annotation = consumerManager.getConsumerAnnotation(e.getValue());
                if (null == annotation) {
                    return m;
                }
                m.put("group", annotation.group());
                m.put("topic", annotation.topic());
                m.put("tag", StringUtil.joinWith("|", Arrays.asList(annotation.tag())));
                return m;
            })
        ));

        List<String> vendorConsumers = new ArrayList<>();
        consumerManager.getVendorConsumers().forEach((consumer, vendorConsumer) -> {
            vendorConsumers.add(vendorConsumer.getVendorInstanceName());
        });

        status.put("vendorConsumers", vendorConsumers);

        return JSONUtil.toJSONString(status);
    }

    @GetMapping(
        value = "/wolf/mq/consumer/restart",
        produces = "application/json"
    )
    public String restart(@ModelAttribute ConsumerRestartCommand command) {
        ConsumerManager consumerManager = mqStarter.getConsumerManager();

        if (command.getMin() > 0 && command.getMax() > 0) {
            Consumer consumer = consumerManager.getConsumer(command.getName());
            if (null == consumer) {
                throw new RuntimeException();
            }
            String group = consumerManager.getConsumerAnnotation(consumer).group();

            mqConfig.updateConsumerGroupConfig(group, command.getMin(), command.getMax());
        }

        consumerManager.restart(command.getName());

        return status();
    }

    @GetMapping(
        value = "/wolf/mq/consumer/suspend",
        produces = "application/json"
    )
    public String restart(@ModelAttribute ConsumerSuspendCommand command) {
        ConsumerManager consumerManager = mqStarter.getConsumerManager();

        consumerManager.suspend(command.getName());

        return status();
    }

    @GetMapping(
        value = "/wolf/mq/consumer/resume",
        produces = "application/json"
    )
    public String restart(@ModelAttribute ConsumerResumeCommand command) {
        ConsumerManager consumerManager = mqStarter.getConsumerManager();

        consumerManager.resume(command.getName());

        return status();
    }
}
