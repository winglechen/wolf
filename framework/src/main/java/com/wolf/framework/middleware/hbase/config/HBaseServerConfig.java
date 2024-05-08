package com.wolf.framework.middleware.hbase.config;

import lombok.Data;

/**
 * HBaseServerConfig
 *
 * @author rocky
 * @since 2023/8/2 15:40
 **/
@Data
public class HBaseServerConfig {
    /**
     * zK address
     */
    private String server;

    /**
     * zk port
     */
    private String port;
}
