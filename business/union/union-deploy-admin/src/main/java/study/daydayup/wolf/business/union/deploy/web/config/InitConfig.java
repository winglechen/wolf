package study.daydayup.wolf.business.union.deploy.web.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * study.daydayup.wolf.business.union.deploy.web.config
 *
 * @author Wingle
 * @since 2019/12/12 2:33 下午
 **/
@Configuration
@ConditionalOnWebApplication
@ComponentScan("study.daydayup.wolf.business.union.api")
public class InitConfig {
}
