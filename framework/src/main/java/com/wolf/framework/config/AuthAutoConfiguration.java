package com.wolf.framework.config;

import com.wolf.framework.layer.web.auth.AuthConfig;
import com.wolf.framework.layer.web.auth.WolfAuth;
import com.wolf.framework.layer.web.session.Session;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.context.WebApplicationContext;

@Configuration
@ConditionalOnProperty("wolf.auth")
public class AuthAutoConfiguration {

    @Bean
    @ConditionalOnBean(Session.class)
    @Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
    public WolfAuth wolfAuth(Session session, AuthConfig authConfig, HttpServletResponse response) {
        return new WolfAuth(session, authConfig, response);
    }


    
}
