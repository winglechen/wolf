package com.wolf.framework.middleware.hbase.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import com.wolf.common.util.collection.MapUtil;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Map;

/**
 * HBaseConfig
 *
 * @author rocky
 * @since 2023/7/25 16:53
 **/
@Slf4j
@org.springframework.context.annotation.Configuration
public class HBaseConfig {

    @Resource
    private HBaseConfigProperties hBaseConfigProperties;

    @Bean
    public Map<String, Admin> admins() {
        Map<String, Admin> admins = MapUtil.empty();
        Map<String, Configuration> configurationMap = configs();
        for (Map.Entry<String, Configuration> entry : configurationMap.entrySet()) {
            Admin admin = connect(entry.getValue());
            if (admin != null) {
                admins.put(entry.getKey(), admin);
            }
        }
        return admins;
    }

    public Map<String, Configuration> configs() {
        Map<String, HBaseServerConfig> servers = hBaseConfigProperties.getServers();
        Map<String, Configuration> configs = MapUtil.empty();
        if (servers == null) {
            return configs;
        }
        servers.forEach((key, value) -> {
            Configuration configuration = HBaseConfiguration.create();
            configuration.set("hbase.zookeeper.quorum", value.getServer());
            configuration.set("hbase.zookeeper.property.clientPort", value.getPort());
            configs.put(key, configuration);
        });
        return configs;
    }

    private Admin connect(Configuration configuration) {
        try {
            Connection connection = ConnectionFactory.createConnection(configuration);
            return connection.getAdmin();
        } catch (IOException e) {
            log.error("connect failed", e);
        }
        return null;
    }
}
