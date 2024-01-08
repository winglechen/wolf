package study.daydayup.wolf.framework.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import study.daydayup.wolf.framework.middleware.rdb.DbExecutor;
import study.daydayup.wolf.framework.middleware.rdb.RDBTemplate;

/**
 * study.daydayup.wolf.framework.config
 *
 * @author Wingle
 * @since 2020/1/6 6:23 下午
 **/
@Configuration
@ComponentScan("study.daydayup.wolf.framework")
public class wolfAutoConfiguration {

    @Bean
    @ConditionalOnBean(JdbcTemplate.class)
    public RDBTemplate dbExecutor(JdbcTemplate jdbcTemplate) {
        DbExecutor dbExecutor = new DbExecutor(jdbcTemplate);
        dbExecutor.init();

        return new RDBTemplate(dbExecutor);
    }

}
