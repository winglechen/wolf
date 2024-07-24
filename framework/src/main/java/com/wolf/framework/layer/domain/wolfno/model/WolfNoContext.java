package com.wolf.framework.layer.domain.wolfno.model;

import com.wolf.common.contract.container.Context;
import com.wolf.framework.layer.domain.wolfno.config.WolfNoConfig;
import lombok.Data;
import org.springframework.jdbc.core.JdbcTemplate;

@Data
public class WolfNoContext implements Context {
    private final WolfNoConfig config;
    private final JdbcTemplate jdbcTemplate;

    private String name;
    private WolfNoStyleEnum style;
    private int type;
    private int datacenter;
    private int shard;

    private int step;
    private double rate;

    public WolfNoContext(WolfNoConfig config, JdbcTemplate jdbcTemplate) {
        this.config = config;
        this.jdbcTemplate = jdbcTemplate;

    }
}
