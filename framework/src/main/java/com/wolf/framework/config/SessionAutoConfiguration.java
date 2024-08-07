package com.wolf.framework.config;

import com.wolf.common.ds.map.ObjectMap;
import com.wolf.framework.layer.web.session.Session;
import com.wolf.framework.layer.web.session.SessionConfig;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.context.WebApplicationContext;

@Configuration
@ConditionalOnProperty("wolf.session")
public class SessionAutoConfiguration {

    @Bean
    @ConditionalOnBean(RedisTemplate.class)
    @Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
    public Session session(RedisTemplate<String, ObjectMap> redisTemplate, SessionConfig sessionConfig, HttpServletRequest request, HttpServletResponse response) {
        Session session = new Session(redisTemplate, sessionConfig);
        session.start(request, response);

        return session;
    }
}
