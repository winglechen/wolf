package study.daydayup.wolf.framework.rpc.dubbo.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface EnableLog {
    /**
     * 是否打印接口响应结果
     * @return boolean
     */
    boolean response() default false;

    boolean paramTypes() default false;
}