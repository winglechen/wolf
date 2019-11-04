package study.daydayup.wolf.framework.layer.rpc;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * study.daydayup.wolf.framework.layer.rpc
 * aliasFor RpcService
 *
 * @author Wingle
 * @since 2019/9/29 4:42 PM
 **/
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@RpcService
public @interface ApiService {
    @AliasFor(annotation = RpcService.class)
    Class<?>[] rpcService() default {};
}
