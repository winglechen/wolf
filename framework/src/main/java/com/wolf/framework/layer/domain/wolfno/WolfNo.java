package com.wolf.framework.layer.domain.wolfno;

import com.wolf.common.util.collection.CollectionUtil;
import com.wolf.framework.layer.domain.wolfno.config.WolfNoConfig;
import com.wolf.framework.layer.domain.wolfno.wolfid.WolfID;
import org.springframework.jdbc.core.JdbcTemplate;

public class WolfNo {
    private final WolfNoConfig config;
    private final JdbcTemplate jdbcTemplate;

    public WolfNo(WolfNoConfig config, JdbcTemplate jdbcTemplate) {
        this.config = config;
        this.jdbcTemplate = jdbcTemplate;

    }

    public void init() {
        if (CollectionUtil.isEmpty(this.config.getNoList())) {
            return;
        }

    }

    /**
     * WolfNo builder api
     *  -> create NoBuilder
     *  -> NoBuilder.create()
     *  -> NoCreator.create()
     *
     * @return NoBuilder
     */
    public WolfID builder() {
        return new WolfID();
    }
}
