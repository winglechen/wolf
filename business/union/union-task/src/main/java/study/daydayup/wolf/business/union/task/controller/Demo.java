package study.daydayup.wolf.business.union.task.controller;

import org.springframework.data.redis.core.RedisTemplate;
import study.daydayup.wolf.framework.layer.context.BeanUtil;

/**
 * study.daydayup.wolf.business.union.task.controller
 *
 * @author Wingle
 * @since 2020/2/18 11:13 下午
 **/
public class Demo {
    public void show() {
        RedisTemplate redis = BeanUtil.getBean("redisTemplate", RedisTemplate.class);

        redis.opsForValue().set("abc", 123);
        Object o = redis.opsForValue().get("abc");

        System.out.println(o);
    }
}
