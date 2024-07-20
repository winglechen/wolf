package com.wolf.framework.rpc;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.annotation.Resource;
import org.springframework.core.annotation.AliasFor;

@Resource
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.ANNOTATION_TYPE})
public @interface Rpc {
    @AliasFor( annotation = Resource.class)
    String name() default "";

    @AliasFor( annotation = Resource.class)
    String lookup() default "";

    @AliasFor( annotation = Resource.class)
    Class<?> type() default Object.class;

    @AliasFor( annotation = Resource.class)
    Resource.AuthenticationType authenticationType() default Resource.AuthenticationType.CONTAINER;

    @AliasFor( annotation = Resource.class)
    boolean shareable() default true;

    @AliasFor( annotation = Resource.class)
    String mappedName() default "";

    @AliasFor( annotation = Resource.class)
    String description() default "";
}
