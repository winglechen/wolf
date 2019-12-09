package study.daydayup.wolf.business.account.auth.agent.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.*;
import org.springframework.web.context.WebApplicationContext;
import study.daydayup.wolf.business.account.auth.agent.Session;
import study.daydayup.wolf.business.account.auth.agent.controller.AuthController;
import study.daydayup.wolf.business.account.auth.agent.filter.WolfSsoFilter;

import javax.annotation.Resource;

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
    @Resource
    private AuthConfig authConfig;

    @Bean
    @Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
    public Session session() {
        Session session = new Session();

        System.out.println("auth auto config: session bean");

        return session;
    }

    @Bean
    public FilterRegistrationBean ssoFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setName("WolfSsoFilter");
        registration.setFilter(new WolfSsoFilter());
        System.out.println("auth auto config: session bean");

        return registration;
    }
}
