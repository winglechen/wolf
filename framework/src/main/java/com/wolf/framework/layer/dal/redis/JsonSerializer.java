package com.wolf.framework.layer.dal.redis;

import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

/**
 * com.wolf.framework.layer.dal.redis
 *
 * @author Wingle
 * @since 2020/5/29 11:50 上午
 **/
public class JsonSerializer implements RedisSerializer<Object> {
    @Override
    public byte[] serialize(Object o) throws SerializationException {
        return new byte[0];
    }

    @Override
    public Object deserialize(byte[] bytes) throws SerializationException {
        return null;
    }
}

