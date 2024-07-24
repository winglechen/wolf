package com.wolf.framework.layer.domain.wolfno.factory;

import com.wolf.framework.layer.domain.wolfno.config.WolfNoConfig;
import com.wolf.framework.layer.domain.wolfno.model.WolfNoStyleEnum;
import com.wolf.framework.layer.domain.wolfno.model.WolfNoContext;
import org.springframework.jdbc.core.JdbcTemplate;

public class WolfNoBuilder {
    private final WolfNoContext context;

    public WolfNoBuilder(WolfNoConfig config, JdbcTemplate jdbcTemplate) {
        this.context = new WolfNoContext(config, jdbcTemplate);
    }

    public WolfNoBuilder style(WolfNoStyleEnum style) {
        context.setStyle(style);
        return this;
    }

    public WolfNoBuilder name(String name) {
        context.setName(name);

        return this;
    }

    public WolfNoBuilder type(int type) {
        context.setType(type);

        return this;
    }

    public WolfNoBuilder datacenter(int datacenter) {
        context.setDatacenter(datacenter);

        return this;
    }

    public WolfNoBuilder shard(int shard) {
        context.setShard(shard);

        return this;
    }

    public WolfNoBuilder step(int step) {
        context.setStep(step);

        return this;
    }

    public WolfNoBuilder rate(int rate) {
        context.setRate(rate);

        return this;
    }

    public String build() {
        WolfNoFactory factory = new WolfNoFactory(context);
        return factory.create();
    }


}
