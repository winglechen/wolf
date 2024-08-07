package com.wolf.framework.config;

import com.wolf.framework.layer.web.auth.AuthConfig;
import com.wolf.framework.layer.web.auth.WolfAuth;
import com.wolf.framework.layer.web.auth.WolfAuthFilter;
import com.wolf.framework.layer.web.session.Session;
import jakarta.servlet.DispatcherType;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;

@Configuration
@ConditionalOnProperty("wolf.auth")
public class AuthAutoConfiguration {

    @Bean
    @ConditionalOnBean(Session.class)
    @Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
    public WolfAuth wolfAuth(Session session, AuthConfig authConfig, HttpServletResponse response) {
        return new WolfAuth(session, authConfig, response);
    }

    @Bean
    @ConditionalOnBean(WolfAuth.class)
    public WolfAuthFilter wolfAuthFilter(WolfAuth wolfAuth, AuthConfig authConfig) {
        return new WolfAuthFilter(wolfAuth, authConfig);
    }

    @Bean
    public FilterRegistrationBean<WolfAuthFilter> disableAutoFilterRegistration(@Qualifier("wolfAuthFilter") WolfAuthFilter filter) {
        FilterRegistrationBean<WolfAuthFilter> registration = new FilterRegistrationBean<>(filter);
        registration.setEnabled(false);

        return registration;
    }


    @Bean
    public FilterRegistrationBean<DelegatingFilterProxy> wolfSsoRegistration() {
        FilterRegistrationBean<DelegatingFilterProxy> registrationBean = new FilterRegistrationBean<>();

        DelegatingFilterProxy delegatingFilterProxy = new DelegatingFilterProxy();
        delegatingFilterProxy.setTargetBeanName("wolfAuthFilter");
        registrationBean.setFilter(delegatingFilterProxy);

        registrationBean.addUrlPatterns("/*");
        registrationBean.setDispatcherTypes(DispatcherType.REQUEST, DispatcherType.FORWARD);
        registrationBean.setName("wolfAuthFilter");

        return registrationBean;
    }


}
