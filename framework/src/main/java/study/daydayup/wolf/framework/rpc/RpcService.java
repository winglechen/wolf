package study.daydayup.wolf.framework.rpc;

import org.apache.dubbo.config.annotation.DubboService;
import org.apache.dubbo.config.annotation.Method;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.core.annotation.AliasFor;
import org.springframework.web.bind.annotation.RestController;

import java.lang.annotation.*;

/**
 * study.daydayup.wolf.framework.rpc
 * 为协议支持提供可能(如：dubbo/spring cloud双协议支持)
 *
 * @author Wingle
 * @since 2019/9/29 4:42 PM
 **/
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@RestController
@DubboService
public @interface RpcService {
    @AliasFor( annotation = RestController.class )
    String value() default "";

    @AliasFor( annotation = DubboService.class )
    Class<?> interfaceClass() default void.class;

    @AliasFor( annotation = DubboService.class )
    String interfaceName() default "";

    @AliasFor( annotation = DubboService.class )
    String version() default "";

    @AliasFor( annotation = DubboService.class )
    String group() default "";

    @AliasFor( annotation = DubboService.class )
    String path() default "";

    @AliasFor( annotation = DubboService.class )
    boolean export() default true;

    @AliasFor( annotation = DubboService.class )
    String token() default "";

    @AliasFor( annotation = DubboService.class )
    boolean deprecated() default false;

    @AliasFor( annotation = DubboService.class )
    boolean dynamic() default true;

    @AliasFor( annotation = DubboService.class )
    String accesslog() default "";

    @AliasFor( annotation = DubboService.class )
    int executes() default 0;

    @AliasFor( annotation = DubboService.class )
    boolean register() default true;

    @AliasFor( annotation = DubboService.class )
    int weight() default 0;

    @AliasFor( annotation = DubboService.class )
    String document() default "";

    @AliasFor( annotation = DubboService.class )
    int delay() default 0;

    /** @deprecated */
    @AliasFor( annotation = DubboService.class )
    String local() default "";

    @AliasFor( annotation = DubboService.class )
    String stub() default "";

    @AliasFor( annotation = DubboService.class )
    String cluster() default "";

    @AliasFor( annotation = DubboService.class )
    String proxy() default "";

    @AliasFor( annotation = DubboService.class )
    int connections() default 0;

    @AliasFor( annotation = DubboService.class )
    int callbacks() default 1;

    @AliasFor( annotation = DubboService.class )
    String onconnect() default "";

    @AliasFor( annotation = DubboService.class )
    String ondisconnect() default "";

    @AliasFor( annotation = DubboService.class )
    String owner() default "";

    @AliasFor( annotation = DubboService.class )
    String layer() default "";

    @AliasFor( annotation = DubboService.class )
    int retries() default 2;

    @AliasFor( annotation = DubboService.class )
    String loadbalance() default "random";

    @AliasFor( annotation = DubboService.class )
    boolean async() default false;

    @AliasFor( annotation = DubboService.class )
    int actives() default 0;

    @AliasFor( annotation = DubboService.class )
    boolean sent() default false;

    @AliasFor( annotation = DubboService.class )
    String mock() default "";

    @AliasFor( annotation = DubboService.class )
    String validation() default "";

    @AliasFor( annotation = DubboService.class )
    int timeout() default 0;

    @AliasFor( annotation = DubboService.class )
    String cache() default "";

    @AliasFor( annotation = DubboService.class )
    String[] filter() default {};

    @AliasFor( annotation = DubboService.class )
    String[] listener() default {};

    @AliasFor( annotation = DubboService.class )
    String[] parameters() default {};

    @AliasFor( annotation = DubboService.class )
    String application() default "";

    @AliasFor( annotation = DubboService.class )
    String module() default "";

    @AliasFor( annotation = DubboService.class )
    String provider() default "";

    @AliasFor( annotation = DubboService.class )
    String[] protocol() default {};

    @AliasFor( annotation = DubboService.class )
    String monitor() default "";

    @AliasFor( annotation = DubboService.class )
    String[] registry() default {};

    @AliasFor( annotation = DubboService.class )
    String tag() default "";

    @AliasFor( annotation = DubboService.class )
    Method[] methods() default {};
}
