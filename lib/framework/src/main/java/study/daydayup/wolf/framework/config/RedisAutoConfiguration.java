package study.daydayup.wolf.framework.config;

import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * study.daydayup.wolf.framework.config.redis
 *
 * @author Wingle
 * @since 2020/5/29 2:34 下午
 **/
@Configuration
@ConditionalOnProperty("spring.redis")
public class RedisAutoConfiguration {

//    @Bean
//    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
//        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
//        redisTemplate.setConnectionFactory(redisConnectionFactory);
//
//        FastJsonRedisSerializer<Object> fastJsonRedisSerializer = new FastJsonRedisSerializer<>(Object.class);
//        redisTemplate.setDefaultSerializer(fastJsonRedisSerializer);
//
//        return redisTemplate;
//    }



}
