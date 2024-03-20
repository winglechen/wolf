package com.onedot.win.common.model.annotation.secure;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
public @interface MaskResponse {

    /**
     * enable whitelist unmask
     */
    boolean enableWhitelist() default true;

    /**
     * whitelist key
     */
    String key() default "maskWhiteList";
}
