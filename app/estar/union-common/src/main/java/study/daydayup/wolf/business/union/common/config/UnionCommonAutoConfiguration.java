package study.daydayup.wolf.business.union.common.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * study.daydayup.wolf.business.union.common.config
 *
 * @author Wingle
 * @since 2020/4/22 4:40 下午
 **/
@Configuration
@ConditionalOnWebApplication
@ComponentScan("study.daydayup.wolf.business.union.common")
public class UnionCommonAutoConfiguration {
}
