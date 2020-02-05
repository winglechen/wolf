package study.daydayup.wolf.common.util;

import com.google.common.base.Joiner;

import javax.annotation.Nullable;

/**
 * study.daydayup.wolf.common.util
 *
 * @author Wingle
 * @since 2019/12/10 9:51 上午
 **/
public class StringUtil {
    public static final String DEFAULT_DELIMITER = "";
    public static final String BLANK = " ";
    public static final String COMMA = ",";
    public static final String COLON = ":";

    public static boolean hasValue(String s, boolean trim) {
        if (!trim) {
            return hasValue(s);
        }

        if (null == s) {
            return false;
        }
        return hasValue(s.trim());
    }

    public static boolean hasValue(String s) {
        return s != null && s.length() > 0;
    }

    public static boolean isTrue(String s) {
        return "true".equalsIgnoreCase(s);
    }

    public static boolean isFalse(String s) {
        return "false".equalsIgnoreCase(s);
    }

    public static boolean containsSpace(String s) {
        return s != null && s.indexOf(' ') != -1;
    }

    public static String join(String f, @Nullable Object s, Object... r) {
        return join(DEFAULT_DELIMITER, f, s, r);
    }

    public static String join(String delimiter, String f, @Nullable Object s, Object... r) {
        if (delimiter == null) {
            delimiter = DEFAULT_DELIMITER;
        }

        Joiner joiner = Joiner.on(delimiter).skipNulls();
        return joiner.join(f, s, r);
    }
}
