package com.wolf.framework.layer.dal.redis;

import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.util.Assert;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * com.wolf.framework.layer.dal.redis
 *
 * @author Wingle
 * @since 2020/5/29 11:50 上午
 **/
public class SimpleSerializer implements RedisSerializer<Object> {
    private final Charset charset;
    public SimpleSerializer() {
        this(StandardCharsets.UTF_8);
    }

    public SimpleSerializer(Charset charset) {
        Assert.notNull(charset, "Charset must not be null");
        this.charset = charset;
    }

    @Override
    public byte[] serialize(Object o) throws SerializationException {
        return o == null ? null : String.valueOf(o).getBytes(charset);
    }

    @Override
    public Object deserialize(byte[] bytes) throws SerializationException {
        return null == bytes ? null : new String(bytes, charset);
    }
}
