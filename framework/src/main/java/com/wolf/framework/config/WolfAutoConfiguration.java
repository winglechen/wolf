package com.wolf.framework.config;

import com.wolf.common.ds.map.ObjectMap;
import com.wolf.framework.layer.web.session.Session;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.context.WebApplicationContext;

/**
 * com.wolf.framework.config
 *
 * @author Wingle
 * @since 2020/1/6 6:23 下午
 **/
@Configuration
@ComponentScan("com.wolf.framework")
public class WolfAutoConfiguration {




}
