package com.onedot.win.framework.middleware.binlog;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ApplicationContextEvent;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import com.onedot.win.common.util.collection.MapUtil;
import com.onedot.win.framework.middleware.binlog.config.BinlogConfig;
import com.onedot.win.framework.middleware.binlog.config.TableConfig;
import com.onedot.win.framework.middleware.mq.MQTemplate;
import com.onedot.win.framework.middleware.mq.config.MQConsumerConfig;
import com.onedot.win.framework.middleware.mq.config.MQConsumerGroupConfig;
import com.onedot.win.framework.middleware.mq.config.MQVendorConfig;
import com.onedot.win.framework.middleware.mq.core.MQVendor;
import com.onedot.win.framework.util.ContextUtil;

import javax.annotation.Resource;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author: YIK
 * @since: 2022/3/4 10:34 AM
 */
@Component
@Slf4j
public class BinlogStarter implements ApplicationListener<ApplicationContextEvent>, Ordered {

    private final AtomicBoolean started = new AtomicBoolean(false);

    @Resource
    private BinlogConfig binlogConfig;

    @Resource
    private MQTemplate mqTemplate;

    /**
     * Handle an application event.
     *
     * @param event the event to respond to
     */
    @Override
    public void onApplicationEvent(ApplicationContextEvent event) {
        if (event instanceof ContextRefreshedEvent) {
            if (event.getApplicationContext().getParent() != null) {
                return;
            }
            start();
        } else if (event instanceof ContextClosedEvent) {
            stop();
        }
    }

    @Override
    public int getOrder() {
        return 0;
    }

    public void start() {
        if (MapUtil.isEmpty(binlogConfig.getTables())) {
            return;
        }

        if (!started.compareAndSet(false, true)) {
            return;
        }

        log.info("[BINLOG] BinlogStarter is starting...");

        startConsumer();;

        log.info("[BINLOG] BinlogStarter has been started.");
    }

    public void stop() {
        if (!started.get()) {
            return;
        }

        log.info("[BINLOG] BinlogStarter is going to shutdown...");

        started.compareAndSet(true, false);

        log.info("[BINLOG] BinlogStarter has been shutdown.");
    }

    private void startConsumer() {
        // todolist@check add binlog config validation

        binlogConfig.getTables().forEach((tableName, binLogConfigMaps) -> {
            BinlogProcessor processor = (BinlogProcessor) ContextUtil.getBean(tableName);
            binLogConfigMaps.forEach((aliDtsTaskId, tableConfig) -> {
                startTableConsumer(tableConfig, processor);
                log.info("[BINLOG] binlog consumer has been started. name={}({}) ", processor.getClass().getSimpleName(), aliDtsTaskId);
            });
        });
    }

    private void startTableConsumer(TableConfig config, BinlogProcessor processor) {
        BinlogConsumer listener = new BinlogConsumer(processor, config.getTableNamePattern());

        // todolist@mq move this to MQConfigConverter.java
        MQVendorConfig vendor = MQVendorConfig.builder()
                .vendorType(MQVendor.VENDOR_KAFKA)
                .vendorId(MQVendor.VENDOR_KAFKA + "(" + config.getNameServer() + ")")
                .nameServer(config.getNameServer())
                .username(config.getUser())
                .password(config.getPassword())
                .enabled(true)
                .build();

        MQConsumerGroupConfig groupConfig = MQConsumerGroupConfig.builder()
                .group(config.getGroup())
                .enabled(true)
                .build();

        MQConsumerConfig.CheckPoint checkPoint = MQConsumerConfig.CheckPoint.builder()
                .force(config.getCheckpoint() != null && config.getCheckpoint().isForce())
                .timestamp(config.getCheckpoint() == null ? null : config.getCheckpoint().getTimestamp())
                .build();

        mqTemplate.consumer(listener)
                .vendor(vendor)
                .topic(config.getTopic())
                .groupConfig(groupConfig)
                .checkPoint(checkPoint)
                .start();
    }

}
