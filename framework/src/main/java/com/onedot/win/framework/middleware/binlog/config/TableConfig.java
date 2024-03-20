package com.onedot.win.framework.middleware.binlog.config;

import lombok.Data;

/**
 * @author weixing
 * @since 2023/3/2 19:56
 */
@Data
public class TableConfig {
    private String nameServer;
    private String topic;
    private String group;
    private Checkpoint checkpoint;

    private String user;
    private String password;
    private String tableNamePattern;
}
