package com.wolf.framework.config;

import com.wolf.common.util.lang.StringUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import com.wolf.framework.middleware.rdb.DbExecutor;
import com.wolf.framework.middleware.rdb.RDBTemplate;

import javax.annotation.PostConstruct;

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

    @Value("${spring.application.name}")
    private String springApplicationName;

    @PostConstruct
    public void rewriteProperty() {
        rewriteNacosProjectName();
    }

    /**
     * set nacos project.name some to spring.application.name
     *
     * @see com.alibaba.nacos.client.utils.AppNameUtils
     */
    private void rewriteNacosProjectName() {
        if (StringUtil.isBlank(springApplicationName)) {
            // let it go, do nothing.
            return;
        }

        String projectName = System.getProperty("project.name");

        if (StringUtil.isNoneBlank(projectName)) {
            // let it go, nothing to do.
            // -D has a higher priority
            return;
        }

        if (null == projectName || StringUtil.isBlank(projectName)) {
            //set project.name some to spring.application.name
            System.setProperty("project.name", springApplicationName);
        }
    }

    @Bean
    @ConditionalOnBean(JdbcTemplate.class)
    public RDBTemplate dbExecutor(JdbcTemplate jdbcTemplate) {
        DbExecutor dbExecutor = new DbExecutor(jdbcTemplate);
        dbExecutor.init();

        return new RDBTemplate(dbExecutor);
    }

}
