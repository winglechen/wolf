package com.onedot.win.framework.middleware.hbase.executor.hbase.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * HBaseFiled
 *
 * @author rocky
 * @since 2023/7/26 15:07
 **/
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface HBaseFiled {

    String family();

    String qualifier();
}
