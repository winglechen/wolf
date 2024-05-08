package com.wolf.common.model.annotation.secure;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
public @interface MaskProperty {

    String mix() default "X";

    int length() default 5;

    /**
     * index of string
     * @return
     */
    int start() default 1;

    /**
     * if end = -1 then mask all length
     * @return
     */
    int end() default -1;

    String type() default "";
}
