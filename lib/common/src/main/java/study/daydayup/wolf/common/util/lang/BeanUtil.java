package study.daydayup.wolf.common.util.lang;

import lombok.NonNull;
import lombok.SneakyThrows;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

/**
 * study.daydayup.wolf.common.util.lang
 *
 * @author Wingle
 * @since 2020/2/14 5:44 下午
 **/
public class BeanUtil {
    public static boolean equals(Object a, Object b) {
        if (a == b) {
            return true;
        }

        return a.equals(b);
    }

    public static boolean isEmpty(Object object) {
        if (null == object) {
            return true;
        }

        Field[] fields = object.getClass().getDeclaredFields();

        try {
            for (Field field : fields) {
                field.setAccessible(true);
                if (null != field.get(object)) {
                    return false;
                }
            }
        } catch (IllegalAccessException e) {
            return true;
        }

        return true;
    }

    public static Map<String, Object> toMap(@NonNull Object object) {
        Map<String, Object> map = new HashMap<>();
        Field[] fields = object.getClass().getDeclaredFields();

        try {
            for (Field field : fields) {
                field.setAccessible(true);
                map.put(field.getName(), field.get(object));
            }
        } catch (IllegalAccessException e) {
            return null;
        }

        return map;
    }

    @SneakyThrows
    public static <T> T toBean(@NonNull Map<String, Object> map, Class<T> beanClass) {
        T object;
        try {
            object = beanClass.newInstance();
            Field[] fields = object.getClass().getDeclaredFields();
            for (Field field : fields) {
                int mod = field.getModifiers();
                if (Modifier.isStatic(mod) || Modifier.isFinal(mod)) {
                    continue;
                }
                field.setAccessible(true);
                if (map.containsKey(field.getName())) {
                    field.set(object, map.get(field.getName()));
                }
            }
        } catch (IllegalAccessException e) {
            return null;
        }

        return object;
    }

}
