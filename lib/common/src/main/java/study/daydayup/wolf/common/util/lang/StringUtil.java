package study.daydayup.wolf.common.util.lang;

import com.google.common.base.CaseFormat;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Iterators;
import study.daydayup.wolf.common.util.collection.ListUtil;

import javax.annotation.Nullable;
import java.util.Collections;

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

    public static boolean isBlank(String s) {
        return isEmpty(s, true);
    }

    public static boolean notBlank(String s) {
        return ! isBlank(s);
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

    public static String join(Object f, @Nullable Object s, Object... r) {
        return joinWith(DEFAULT_DELIMITER, f, s, r);
    }

    public static String joinWith(String delimiter, Object f, @Nullable Object s, Object... r) {
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
        String sSuffix = s.substring(sLen-suffixLen);
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

    public static String camelTo(String s) {
        return camelTo(s, null);
    }

    public static String camelTo(String s, String separator) {
        String result = underscore(s);
        if (null == separator || separator.equals(UNDERSCORE)) {
            return result;
        }

        return result.replace(UNDERSCORE, separator);
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

    public static String capitalize(String str) {
        if (str == null) {
            return null;
        }

        char[] ch = str.toCharArray();
        if (0 == ch.length) {
            return str;
        }

        if (ch[0] >= 'a' && ch[0] <= 'z') {
            ch[0] = (char) (ch[0] - 32);
        } else {
            return str;
        }

        return new String(ch);
    }

    public static String uncapitalize(String str) {
        if (str == null) {
            return null;
        }

        char[] ch = str.toCharArray();
        if (0 == ch.length) {
            return str;
        }

        if (ch[0] >= 'A' && ch[0] <= 'Z') {
            ch[0] = (char) (ch[0] + 32);
        } else {
            return str;
        }

        return new String(ch);
    }

    public static String ucWords(String s) {
        return ucWords(s, BLANK);
    }

    public static String ucWords(String s, String separator) {
        if (isEmpty(s, true)) {
            return s;
        }

        String[] sArray = split(s, separator);
        for (int i = 0, len=sArray.length; i < len; i++) {
            sArray[i] = capitalize(sArray[i]);
        }

        return String.join(separator, sArray);
    }

    public static String lcWords(String s) {
        return lcWords(s, BLANK);
    }

    public static String lcWords(String s, String separator) {
        if (isEmpty(s, true)) {
            return s;
        }

        String[] sArray = split(s, separator);
        for (int i = 0, len=sArray.length; i < len; i++) {
            if (isEmpty(sArray[i])) {
                continue;
            }
            sArray[i] = uncapitalize(sArray[i]);
        }

        return String.join(separator, sArray);
    }

    public static String addSlash(String s) {
        return s;
    }

    public static String[] split(String s, String separator) {
        if (isEmpty(separator)) {
            return new String[]{s};
        }

        Iterable<String> i = Splitter.on(separator).split(s);
        return Iterators.toArray(i.iterator(), String.class);
    }

}
