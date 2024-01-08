package study.daydayup.wolf.framework.middleware.hbase.executor.hbase.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * HBaseTableName
 *
 * @author rocky <luojing@onion-pay.com>
 * @since 2023/7/27 17:13
 **/
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface HBaseTableName {
    String name();

    String source();
}
