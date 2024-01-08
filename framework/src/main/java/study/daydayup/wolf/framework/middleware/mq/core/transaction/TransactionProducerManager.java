package study.daydayup.wolf.framework.middleware.mq.core.transaction;

import ch.qos.logback.core.util.EnvUtil;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import study.daydayup.wolf.common.util.collection.ListUtil;
import study.daydayup.wolf.common.util.lang.StringUtil;
import study.daydayup.wolf.framework.middleware.mq.annotation.MQTransaction;
import study.daydayup.wolf.framework.middleware.mq.config.MQConfig;
import study.daydayup.wolf.framework.middleware.mq.config.MQTransactionProducerConfig;
import study.daydayup.wolf.framework.util.ContextUtil;
import study.daydayup.wolf.framework.util.EnvHolderUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author weixing
 * @since 2022/10/11 20:48
 */
@Slf4j
public class TransactionProducerManager {

    private final MQConfig mqConfig;

    private final VendorTransactionProducerFactory vendorTransactionProducerFactory;
    /**
     * Map: <Topic, List<VendorTransactionProducer>>
     */
    @Getter
    private final Map<String, List<VendorTransactionProducer>> vendorProducers = new HashMap<>();

    private final Map<String /* topic */, Map<String /* tag */, TransactionChecker>> transactionCheckerMap = new HashMap<>();

    public TransactionProducerManager(MQConfig mqConfig) {
        this.mqConfig = mqConfig;
        this.vendorTransactionProducerFactory = new VendorTransactionProducerFactory();
    }

    public void registerWithAnnotation(String name, TransactionChecker transactionChecker) {
        //Class<?> targetClass = AopUtils.getTargetClass(transactionProducer);
        Class<?> targetClass = transactionChecker.getClass();
        MQTransaction annotation = targetClass.getAnnotation(MQTransaction.class);
        if (null == annotation) {
            log.error("[MQ] transaction producer can not start, because the MQTransaction annotation not configured on class. name={}", targetClass.getName());
            return;
        }

        // Some transaction checker only available on specify env.
        if (StringUtil.notEmpty(annotation.env()) && !annotation.env().equals(EnvHolderUtil.getEnv())) {
            return;
        }

        TransactionCheckerRegistry.register(annotation.topic(), annotation.tag(), transactionChecker);
    }

    public void start() {
        TransactionCheckerRegistry.get().forEach((topic, transactionCheckerMap) -> {
            MQTransactionProducerConfig config = mqConfig.getTransactionProducerConfig(topic);
            start(config);
        });
    }

    public void start(MQTransactionProducerConfig config) {
        if (!config.getVendorConfig().isEnabled()) {
            log.info("[MQ] transaction producer can not start, because the vendor is disabled. vendorId={}", config.getVendorConfig().getVendorId());
            return;
        }

        for (int i = 1; i <= config.getVendorConfig().getTransactionProducerNum(); i++) {
            VendorTransactionProducer producer = vendorTransactionProducerFactory.create(config);
            producer.start();

            hold(config.getTopic(), producer);

            log.info("[MQ] transaction producer has been started. producerId={}, vendorId={}, group={}, topic={}, checkers={}, minCheckThreadNum={}, maxCheckThreadNum={}, maxRequestHold={}",
                producer.getVendorInstanceName(),
                config.getVendorConfig().getVendorId(),
                config.getGroup(),
                config.getTopic(),
                TransactionCheckerRegistry.get(config.getTopic()).entrySet().stream().collect(
                    Collectors.toMap(Map.Entry::getKey, entry -> entry.getValue().getClass().getSimpleName())
                ),
                config.getMinCheckThreadNum(),
                config.getMaxCheckThreadNum(),
                config.getMaxRequestHold()
            );
        }
    }

    public void shutdownAll() {
        vendorProducers.values().forEach(producerList -> {
            producerList.forEach(producer -> {
                producer.shutdown();
            });
        });
    }

    public VendorTransactionProducer get(String uniqueTopic) {
        List<VendorTransactionProducer> list = vendorProducers.get(uniqueTopic);
        if (ListUtil.isEmpty(list)) {
            throw new RuntimeException();
        }

        // todolist@mq random pick up
        return list.get(0);
    }

    private void hold(String uniqueTopic, VendorTransactionProducer producer) {
        List<VendorTransactionProducer> list = vendorProducers.get(uniqueTopic);
        if (null == list) {
            list = new ArrayList<>();
            vendorProducers.put(uniqueTopic, list);
        }
        list.add(producer);
    }
}
