package study.daydayup.wolf.framework.config;

import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializeWriter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.data.redis.LettuceClientConfigurationBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * study.daydayup.wolf.framework.config.redis
 *
 * @author Wingle
 * @since 2020/5/29 2:34 下午
 **/
@Configuration
//@ConditionalOnProperty("spring.redis")
public class RedisAutoConfiguration {

    @Bean
    @ConditionalOnProperty(
        value = {"spring.redis.ssl"},
        havingValue = "true"
    )
    public LettuceClientConfigurationBuilderCustomizer lettuceClientConfigurationBuilderCustomizer(){
        return (clientConfigurationBuilder) ->{
            clientConfigurationBuilder.useSsl().disablePeerVerification();
        };
    }

    @Bean
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);

        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        FastJsonRedisSerializer<Object> fastJsonRedisSerializer = new FastJsonRedisSerializer<>(Object.class);

        FastJsonConfig fastJsonConfig = fastJsonRedisSerializer.getFastJsonConfig();
        SerializeConfig serializeConfig = fastJsonConfig.getSerializeConfig();

        serializeConfig.put(LocalDateTime.class, (serializer, object, fieldName, fieldType, features) -> {
            SerializeWriter out = serializer.out;
            if (object == null) {
                out.writeNull();
                return;
            }
            out.writeString(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format((LocalDateTime) object));
        });
        serializeConfig.put(LocalDate.class, (serializer, object, fieldName, fieldType, features) -> {
            SerializeWriter out = serializer.out;
            if (object == null) {
                out.writeNull();
                return;
            }
            out.writeString(DateTimeFormatter.ofPattern("yyyy-MM-dd").format((LocalDate) object));
        });
        serializeConfig.put(LocalTime.class, (serializer, object, fieldName, fieldType, features) -> {
            SerializeWriter out = serializer.out;
            if (object == null) {
                out.writeNull();
                return;
            }
            out.writeString(DateTimeFormatter.ofPattern("HH:mm:ss").format((LocalTime) object));
        });
        fastJsonConfig.setSerializeConfig(serializeConfig);
        fastJsonConfig.setFeatures(Feature.SupportAutoType);
        fastJsonConfig.setSerializerFeatures(SerializerFeature.WriteClassName);

        redisTemplate.setEnableDefaultSerializer(true);
        redisTemplate.setDefaultSerializer(fastJsonRedisSerializer);

        redisTemplate.setKeySerializer(stringRedisSerializer);
        redisTemplate.setHashKeySerializer(stringRedisSerializer);

        redisTemplate.setValueSerializer(fastJsonRedisSerializer);
        redisTemplate.setHashValueSerializer(fastJsonRedisSerializer);
        redisTemplate.afterPropertiesSet();

        return redisTemplate;
    }



}
