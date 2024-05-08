package com.wolf.framework.middleware.binlog.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * @author weixing
 * @since 2023/3/2 19:49
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "wolf.binlog", ignoreInvalidFields = true)
public class BinlogConfig {
    private Map<String/*consumer bean name*/, Map<String/*ali dts task id*/, TableConfig>> tables;
}
