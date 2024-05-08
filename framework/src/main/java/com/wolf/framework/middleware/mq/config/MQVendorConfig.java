package com.wolf.framework.middleware.mq.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.wolf.framework.layer.api.Config;

import java.util.Set;

/**
 * @author weixing
 * @since 2022/10/12 14:32
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MQVendorConfig implements Config {
    private String vendorId;
    private String vendorType;
    private String nameServer;
    private String endpoints;
    private String appKey;
    private String appSecret;
    private String username;
    private String password;
    private Set<String> topics;
    private String defaultProducerGroup;
    private int producerNum = 1;
    // todolist@mq default transaction producer num for each topic
    private int transactionProducerNum = 1;
    private boolean enabled;
}
