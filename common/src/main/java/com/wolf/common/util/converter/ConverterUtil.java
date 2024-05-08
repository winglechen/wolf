package com.wolf.common.util.converter;

import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * ConverterUtil
 *
 * @author rocky
 * @since 2022/12/14 15:15
 **/
public class ConverterUtil {
    public static <T> T convert(Object source, Class<T> clazz) {
        if (source == null) {
            return null;
        }
        T target = null;
        try {
            target = clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            return null;
        }
        BeanUtils.copyProperties(source, target);
        return target;
    }

    public static <T> List<T> convertList(List source, Class<T> clazz) {
        List<T> target = new ArrayList<>();
        for (Object o : source) {
            target.add(convert(o, clazz));
        }
        return target;
    }


}
