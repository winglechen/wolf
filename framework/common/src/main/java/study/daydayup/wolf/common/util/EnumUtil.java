package study.daydayup.wolf.common.util;

import study.daydayup.wolf.common.lang.enums.CodeBasedEnum;
import study.daydayup.wolf.common.lang.exception.enums.EnumCodeNotSupportException;

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

        throw new EnumCodeNotSupportException("code: " + code + "is not supported");
    }
}
