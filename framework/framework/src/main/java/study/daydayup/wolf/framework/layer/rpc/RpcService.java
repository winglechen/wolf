package study.daydayup.wolf.framework.layer.rpc;

import org.apache.dubbo.config.annotation.Method;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.core.annotation.AliasFor;
import org.springframework.web.bind.annotation.RestController;

import java.lang.annotation.*;

/**
 * study.daydayup.wolf.framework.layer.rpc
 * 为协议支持提供可能(如：dubbo/spring cloud双协议支持)
 *
 * @author Wingle
 * @since 2019/9/29 4:42 PM
 **/
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@RestController
@Service
public @interface RpcService {
    @AliasFor( annotation = RestController.class )
    String value() default "";

    @AliasFor( annotation = Service.class )
    Class<?> interfaceClass() default void.class;

    @AliasFor( annotation = Service.class )
    String interfaceName() default "";

    @AliasFor( annotation = Service.class )
    String version() default "";

    @AliasFor( annotation = Service.class )
    String group() default "";

    @AliasFor( annotation = Service.class )
    String path() default "";

    @AliasFor( annotation = Service.class )
    boolean export() default true;

    @AliasFor( annotation = Service.class )
    String token() default "";

    @AliasFor( annotation = Service.class )
    boolean deprecated() default false;

    @AliasFor( annotation = Service.class )
    boolean dynamic() default true;

    @AliasFor( annotation = Service.class )
    String accesslog() default "";

    @AliasFor( annotation = Service.class )
    int executes() default 0;

    @AliasFor( annotation = Service.class )
    boolean register() default true;

    @AliasFor( annotation = Service.class )
    int weight() default 0;

    @AliasFor( annotation = Service.class )
    String document() default "";

    @AliasFor( annotation = Service.class )
    int delay() default 0;

    /** @deprecated */
    @AliasFor( annotation = Service.class )
    String local() default "";

    @AliasFor( annotation = Service.class )
    String stub() default "";

    @AliasFor( annotation = Service.class )
    String cluster() default "";

    @AliasFor( annotation = Service.class )
    String proxy() default "";

    @AliasFor( annotation = Service.class )
    int connections() default 0;

    @AliasFor( annotation = Service.class )
    int callbacks() default 1;

    @AliasFor( annotation = Service.class )
    String onconnect() default "";

    @AliasFor( annotation = Service.class )
    String ondisconnect() default "";

    @AliasFor( annotation = Service.class )
    String owner() default "";

    @AliasFor( annotation = Service.class )
    String layer() default "";

    @AliasFor( annotation = Service.class )
    int retries() default 2;

    @AliasFor( annotation = Service.class )
    String loadbalance() default "random";

    @AliasFor( annotation = Service.class )
    boolean async() default false;

    @AliasFor( annotation = Service.class )
    int actives() default 0;

    @AliasFor( annotation = Service.class )
    boolean sent() default false;

    @AliasFor( annotation = Service.class )
    String mock() default "";

    @AliasFor( annotation = Service.class )
    String validation() default "";

    @AliasFor( annotation = Service.class )
    int timeout() default 0;

    @AliasFor( annotation = Service.class )
    String cache() default "";

    @AliasFor( annotation = Service.class )
    String[] filter() default {};

    @AliasFor( annotation = Service.class )
    String[] listener() default {};

    @AliasFor( annotation = Service.class )
    String[] parameters() default {};

    @AliasFor( annotation = Service.class )
    String application() default "";

    @AliasFor( annotation = Service.class )
    String module() default "";

    @AliasFor( annotation = Service.class )
    String provider() default "";

    @AliasFor( annotation = Service.class )
    String[] protocol() default {};

    @AliasFor( annotation = Service.class )
    String monitor() default "";

    @AliasFor( annotation = Service.class )
    String[] registry() default {};

    @AliasFor( annotation = Service.class )
    String tag() default "";

    @AliasFor( annotation = Service.class )
    Method[] methods() default {};
}
