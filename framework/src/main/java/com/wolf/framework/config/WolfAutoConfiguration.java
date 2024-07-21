package com.wolf.framework.config;

import com.wolf.common.util.lang.StringUtil;
import com.wolf.framework.middleware.rdb.DbExecutor;
import com.wolf.framework.middleware.rdb.RDBTemplate;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * com.wolf.framework.config
 *
 * @author Wingle
 * @since 2020/1/6 6:23 下午
 **/
@Configuration
@ComponentScan("com.wolf.framework")
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
