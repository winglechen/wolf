package com.onedot.win.framework.middleware.hbase.executor.hbase.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * HBaseTableName
 *
 * @author rocky
 * @since 2023/7/27 17:13
 **/
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface HBaseTableName {
    String name();

    String source();
}
