package study.daydayup.wolf.framework.middleware.hbase.config;

import lombok.Data;

/**
 * HBaseServerConfig
 *
 * @author rocky <luojing@onion-pay.com>
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
