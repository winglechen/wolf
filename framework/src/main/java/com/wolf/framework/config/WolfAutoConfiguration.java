package com.wolf.framework.config;

import com.wolf.framework.layer.domain.uid.WolfID;
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
    public WolfID wolfID(JdbcTemplate jdbcTemplate) {
        return new WolfID(jdbcTemplate);
    }

}
