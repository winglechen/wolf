package com.onedot.win.framework.config;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import com.onedot.win.framework.middleware.rdb.DbExecutor;
import com.onedot.win.framework.middleware.rdb.RDBTemplate;

/**
 * com.onedot.win.framework.config
 *
 * @author Wingle
 * @since 2020/1/6 6:23 下午
 **/
@Configuration
@ComponentScan("com.onedot.win.framework")
@AutoConfigureAfter(JdbcTemplateAutoConfiguration.class)
public class WolfAutoConfiguration {

    @Bean
    @ConditionalOnBean(JdbcTemplate.class)
    public RDBTemplate dbExecutor(JdbcTemplate jdbcTemplate) {
        DbExecutor dbExecutor = new DbExecutor(jdbcTemplate);
        dbExecutor.init();

        return new RDBTemplate(dbExecutor);
    }

}
