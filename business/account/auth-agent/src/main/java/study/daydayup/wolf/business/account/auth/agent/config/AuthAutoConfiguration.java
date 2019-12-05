package study.daydayup.wolf.business.account.auth.agent.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.*;
import org.springframework.web.context.WebApplicationContext;
import study.daydayup.wolf.business.account.auth.agent.Session;

/**
 * study.daydayup.wolf.business.account.auth.agent.config
 *
 * @author Wingle
 * @since 2019/12/5 9:49 上午
 **/
@EnableConfigurationProperties(AuthConfig.class)
@Configuration
@ConditionalOnWebApplication
@ComponentScan("study.daydayup.wolf.business.account.auth.agent")
public class AuthAutoConfiguration {

    @Bean
    @Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
    public Session session() {
        Session session = new Session();

        return session;
    }

    @Bean
    public FilterRegistrationBean ssoFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();

        return registration;
    }
}
