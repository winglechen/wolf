package com.wolf.framework.util;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * com.wolf.framework.util
 *
 * @author Wingle
 * @since 2020/9/10 4:09 下午
 **/
public class ValidatorUtil {
    public static String formatBindingResult(BindingResult result) {
        if (null == result || !result.hasErrors()) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        List<FieldError> ls = result.getFieldErrors();
        for (FieldError error : ls) {
            sb.append(error.getField())
                    .append(": ")
                    .append(error.getDefaultMessage())
                    .append(";")
                    ;
        }

        return sb.toString();
    }

    public static <T> T valid(@Validated T o) {
        return o;
    }
}
