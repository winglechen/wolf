package com.wolf.framework.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * com.wolf.framework.config
 *
 * @author Wingle
 * @since 2020/1/6 6:23 下午
 **/
@Configuration
@ComponentScan("com.wolf.framework")
public class WolfAutoConfiguration {


//    @Bean
////    @ConditionalOnProperty("wolf.session")
//    @Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
//    public Session session(RedisTemplate<String, ObjectMap> redisTemplate, SessionConfig sessionConfig, HttpServletRequest request, HttpServletResponse response) {
//        Session session = new Session(redisTemplate, sessionConfig);
//        session.start(request, response);
//
//        return session;
//    }


}
