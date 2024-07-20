package com.wolf.framework.rpc;

import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.Method;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * com.wolf.framework.rpc
 *
 * @author Wingle
 * @since 2020/5/1 10:28 下午
 **/
@Documented
@DubboReference
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.ANNOTATION_TYPE})
public @interface RpcBak {
    @AliasFor( annotation = DubboReference.class )
    Class<?> interfaceClass() default void.class;

    @AliasFor( annotation = DubboReference.class )
    String interfaceName() default "";

    @AliasFor( annotation = DubboReference.class )
    String version() default "";

    @AliasFor( annotation = DubboReference.class )
    String group() default "";

    @AliasFor( annotation = DubboReference.class )
    String url() default "";

    @AliasFor( annotation = DubboReference.class )
    String client() default "";

    @AliasFor( annotation = DubboReference.class )
    boolean generic() default false;

    @AliasFor( annotation = DubboReference.class )
    boolean injvm() default true;

    @AliasFor( annotation = DubboReference.class )
    boolean check() default true;

    @AliasFor( annotation = DubboReference.class )
    boolean init() default false;

    @AliasFor( annotation = DubboReference.class )
    boolean lazy() default false;

    @AliasFor( annotation = DubboReference.class )
    boolean stubevent() default false;

    @AliasFor( annotation = DubboReference.class )
    String reconnect() default "";

    @AliasFor( annotation = DubboReference.class )
    boolean sticky() default false;

    @AliasFor( annotation = DubboReference.class )
    String proxy() default "";

    @AliasFor( annotation = DubboReference.class )
    String stub() default "";

    @AliasFor( annotation = DubboReference.class )
    String cluster() default "";

    @AliasFor( annotation = DubboReference.class )
    int connections() default 0;

    @AliasFor( annotation = DubboReference.class )
    int callbacks() default 0;

    @AliasFor( annotation = DubboReference.class )
    String onconnect() default "";

    @AliasFor( annotation = DubboReference.class )
    String ondisconnect() default "";

    @AliasFor( annotation = DubboReference.class )
    String owner() default "";

    @AliasFor( annotation = DubboReference.class )
    String layer() default "";

    @AliasFor( annotation = DubboReference.class )
    int retries() default 2;

    @AliasFor( annotation = DubboReference.class )
    String loadbalance() default "";

    @AliasFor( annotation = DubboReference.class )
    boolean async() default false;

    @AliasFor( annotation = DubboReference.class )
    int actives() default 0;

    @AliasFor( annotation = DubboReference.class )
    boolean sent() default false;

    @AliasFor( annotation = DubboReference.class )
    String mock() default "";

    @AliasFor( annotation = DubboReference.class )
    String validation() default "";

    @AliasFor( annotation = DubboReference.class )
    int timeout() default 0;

    @AliasFor( annotation = DubboReference.class )
    String cache() default "";

    @AliasFor( annotation = DubboReference.class )
    String[] filter() default {};

    @AliasFor( annotation = DubboReference.class )
    String[] listener() default {};

    @AliasFor( annotation = DubboReference.class )
    String[] parameters() default {};

    @AliasFor( annotation = DubboReference.class )
    String application() default "";

    @AliasFor( annotation = DubboReference.class )
    String module() default "";

    @AliasFor( annotation = DubboReference.class )
    String consumer() default "";

    @AliasFor( annotation = DubboReference.class )
    String monitor() default "";

    @AliasFor( annotation = DubboReference.class )
    String[] registry() default {};

    @AliasFor( annotation = DubboReference.class )
    String protocol() default "";

    @AliasFor( annotation = DubboReference.class )
    String tag() default "";

    @AliasFor( annotation = DubboReference.class )
    Method[] methods() default {};

    @AliasFor( annotation = DubboReference.class )
    String id() default "";
}
