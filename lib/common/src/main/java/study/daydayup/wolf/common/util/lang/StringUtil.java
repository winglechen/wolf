package study.daydayup.wolf.common.util.lang;

import com.google.common.base.CaseFormat;
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
    public static final String EMPTY = "";
    public static final String BLANK = " ";
    public static final String COMMA = ",";
    public static final String COLON = ":";
    public static final String QUOTE = "`";
    public static final String UNDERSCORE = "_";


    public static boolean notEmpty(String s, boolean trim) {
        if (!trim) {
            return notEmpty(s);
        }

        if (null == s) {
            return false;
        }
        return notEmpty(s.trim());
    }

    public static boolean isEmpty(String s, boolean trim) {
        return ! notEmpty(s, trim);
    }

    public static boolean isEmpty(String s) {
        return ! notEmpty(s);
    }

    public static boolean notEmpty(String s) {
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
        return joinWith(DEFAULT_DELIMITER, f, s, r);
    }

    public static String joinWith(String delimiter, String f, @Nullable Object s, Object... r) {
        if (delimiter == null) {
            delimiter = DEFAULT_DELIMITER;
        }

        Joiner joiner = Joiner.on(delimiter).skipNulls();
        return joiner.join(f, s, r);
    }

    public static String quote(String keyword) {
        return quote(keyword, false);
    }

    public static String quote(String keyword, boolean multi) {
        if ("*".equals(keyword.trim())) {
            return keyword;
        }

        if (!multi) {
            return join(QUOTE, keyword.trim(), QUOTE);
        }

        String[] keywords = keyword.split(COMMA);
        StringBuilder result = new StringBuilder();

        boolean isFirst = true;
        for (String s : keywords) {
            if (!isFirst) {
                result.append(COMMA);
            }
            isFirst = false;

            result.append(QUOTE).append(s.trim()).append(QUOTE);
        }

        return result.toString();
    }

    public static String ltrim(String s, String prefix) {
        int prefixLen = prefix.length();
        if (0 == prefixLen) {
            return s;
        }

        String sPrefix = s.substring(0, prefixLen);
        if (!sPrefix.equals(prefix)) {
            return s;
        }

        return s.substring(prefixLen);
    }

    public static String rtrim(String s, String suffix) {
        int suffixLen = suffix.length();
        if (0 == suffixLen) {
            return s;
        }

        int sLen = s.length();
        String sSuffix = s.substring(suffixLen, sLen);
        if (!sSuffix.equals(suffix)) {
            return s;
        }

        return s.substring(0, sLen-suffixLen);
    }


    public static String camel(String s) {
        return lowerCamel(s);
    }

    public static String camel(String s, String separator) {
        return lowerCamel(s, separator);
    }

    public static String lowerCamel(String s, String separator) {
        if (null == separator || separator.equals(UNDERSCORE)) {
            return lowerCamel(s);
        }
        return lowerCamel(s.replace(separator, UNDERSCORE));
    }

    public static String lowerCamel(String s) {
        return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, s);
    }

    public static String upperCamel(String s, String separator) {
        if (null == separator || separator.equals(UNDERSCORE)) {
            return upperCamel(s);
        }
        return upperCamel(s.replace(separator, UNDERSCORE));
    }

    public static String upperCamel(String s) {
        return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, s);
    }

    public static String underscore(String s) {
        return lowerUnderscore(s);
    }

    public static String lowerUnderscore(String s) {
        return CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, s);
    }

    public static String upperUnderscore(String s) {
        return CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, s);
    }

}
