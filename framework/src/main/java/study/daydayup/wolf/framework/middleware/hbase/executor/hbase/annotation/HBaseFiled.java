package study.daydayup.wolf.framework.middleware.hbase.executor.hbase.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * HBaseFiled
 *
 * @author rocky <luojing@onion-pay.com>
 * @since 2023/7/26 15:07
 **/
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface HBaseFiled {

    String family();

    String qualifier();
}
