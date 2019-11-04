package study.daydayup.wolf.framework.layer.rpc;

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
    @AliasFor(
            annotation = RestController.class
    )
    String value() default "";

    @AliasFor(
            annotation = Service.class
    )
    Class<?>[] service() default {};

}
