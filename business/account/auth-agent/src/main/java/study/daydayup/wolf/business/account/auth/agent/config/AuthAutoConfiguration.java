package study.daydayup.wolf.business.account.auth.agent.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.*;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;
import study.daydayup.wolf.business.account.auth.agent.Session;
import study.daydayup.wolf.business.account.auth.agent.filter.WolfSsoFilter;

import javax.servlet.DispatcherType;

/**
 * study.daydayup.wolf.business.account.auth.agent.config
 *
 * @author Wingle
 * @since 2019/12/5 9:49 上午
 **/
@Configuration
@ConditionalOnWebApplication
@ComponentScan("study.daydayup.wolf.business.account.auth.agent")
public class AuthAutoConfiguration {
    @Bean
    @Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
    public Session session() {
        return new Session();
    }

    @Bean(name="wolfSsoFilter")
    public WolfSsoFilter wolfSsoFilter() {
        return new WolfSsoFilter();
    }

    @Bean
    public FilterRegistrationBean<WolfSsoFilter> disableAutoFilterRegistration(@Qualifier("wolfSsoFilter") WolfSsoFilter filter) {
        FilterRegistrationBean<WolfSsoFilter> registration = new FilterRegistrationBean<>(filter);
            registration.setEnabled(false);

        return registration;
    }

    @Bean
    public FilterRegistrationBean<DelegatingFilterProxy> wolfSsoRegistration(){
        FilterRegistrationBean<DelegatingFilterProxy> registrationBean = new FilterRegistrationBean<>();

        DelegatingFilterProxy delegatingFilterProxy = new DelegatingFilterProxy();
        delegatingFilterProxy.setTargetBeanName("wolfSsoFilter");
        registrationBean.setFilter(delegatingFilterProxy);

        registrationBean.addUrlPatterns("/*");
        registrationBean.setDispatcherTypes(DispatcherType.REQUEST, DispatcherType.FORWARD);
        registrationBean.setName("wolfSsoFilter");

        return registrationBean;
    }

}
