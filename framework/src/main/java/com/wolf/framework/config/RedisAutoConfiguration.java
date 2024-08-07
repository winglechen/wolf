package com.wolf.framework.config;


import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONReader;
import com.alibaba.fastjson2.JSONWriter;
import com.alibaba.fastjson2.filter.Filter;
import com.wolf.common.util.collection.ArrayUtil;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * com.wolf.framework.config.redis
 *
 * @author Wingle
 * @since 2020/5/29 2:34 下午
 **/
@Slf4j
@Configuration
@ConditionalOnProperty("spring.redis")
public class RedisAutoConfiguration {

    @Bean
    @ConditionalOnProperty("spring.redis")
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);

        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        FastJson2RedisSerializer<Object> fastJson2RedisSerializer = new FastJson2RedisSerializer<>(Object.class);

        redisTemplate.setEnableDefaultSerializer(true);
        redisTemplate.setDefaultSerializer(fastJson2RedisSerializer);

        redisTemplate.setKeySerializer(stringRedisSerializer);
        redisTemplate.setHashKeySerializer(stringRedisSerializer);

        redisTemplate.setValueSerializer(fastJson2RedisSerializer);
        redisTemplate.setHashValueSerializer(fastJson2RedisSerializer);

        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

     public static class FastJson2RedisSerializer<T> implements RedisSerializer<T> {
        //TODO: use config
        static final Filter AUTO_TYPE_FILTER = JSONReader.autoTypeFilter(
            "com.wolf", "com.one"
        );
        private final Class<T> clazz;

        public FastJson2RedisSerializer(Class<T> clazz) {
            super();
            this.clazz = clazz;
        }

        @Override
        public byte[] serialize(T t) throws SerializationException {
            if (Objects.isNull(t)) {
                return new byte[0];
            }
            try {
                return JSON.toJSONBytes(t, JSONWriter.Feature.WriteClassName);
            } catch (Exception e) {
                log.error("Fastjson2 serialize error：{}", e.getMessage());
                throw new SerializationException("Can't serialize : " + e.getMessage(), e);
            }
        }

        @Override
        public T deserialize(byte[] bytes) throws SerializationException {
            if (ArrayUtil.isEmpty(bytes)) {
                return null;
            }
            try {
                return JSON.parseObject(bytes, clazz, AUTO_TYPE_FILTER);
            } catch (Exception e) {
                log.error("Fastjson2 deserialize error ：{}", e.getMessage());
                throw new SerializationException("Can't deserialize :" + e.getMessage(), e);
            }
        }
    }




}
