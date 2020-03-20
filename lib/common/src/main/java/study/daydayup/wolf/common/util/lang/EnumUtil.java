package study.daydayup.wolf.common.util.lang;

import study.daydayup.wolf.common.lang.enums.CodeBasedEnum;
import study.daydayup.wolf.common.lang.exception.enums.UnsupportedEnumCodeException;

/**
 * study.daydayup.wolf.common.util
 *
 * @author Wingle
 * @since 2019/9/29 10:22 PM
 **/
public class EnumUtil {
    public static <T extends CodeBasedEnum> T codeOf(int code, Class<T> enumType) {
        for(T e : enumType.getEnumConstants()) {
            if (code == e.getCode()) {
                return e;
            }
        }

        throw new UnsupportedEnumCodeException("code: " + code + "is not supported");
    }

    public static <T extends CodeBasedEnum> T nameOf(String name, Class<T> enumType) {
        for(T e : enumType.getEnumConstants()) {
            if (e.getName().equals(name)) {
                return e;
            }
        }

        throw new UnsupportedEnumCodeException( "name: " + name + "is not supported");
    }
}
