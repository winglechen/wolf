package com.onedot.win.framework.security.sensitive;

import com.onedot.win.common.util.lang.StringUtil;

public class IndiaSensitiveInformation {
    public static String mask(String text, int num) {
        if (StringUtil.isBlank(text)) {
            return text;
        }

        int len = text.length();
        if (len < num) {
            return StringUtil.repeat("X", len);
        }

        return StringUtil.join(
                text.substring(0, len - num),
                StringUtil.repeat("X", num)
        );
    }

    public static String maskPhone(String text) {
        if (StringUtil.isBlank(text) || text.length() < 5) {
            return text;
        }

        return StringUtil.join(
                text.substring(0, 5),
                StringUtil.repeat("X", text.length() - 5)
        );
    }


    public static String maskCardNo(String text) {
        return mask(text, 5);
    }

    public static String maskEmail(String text) {
        if (StringUtil.isBlank(text)) {
            return text;
        }

        String prefix = StringUtil.substringTo(text, "@");
        if (StringUtil.isBlank(prefix)) {
            return text;
        }

        String suffix = StringUtil.substringFrom(text, "@");
        String mask = mask(prefix, 4);
        return StringUtil.join(mask, "@", suffix);
    }

    public static String mask(String text, String mix, int num) {
        if (StringUtil.isBlank(text)) {
            return text;
        }

        int len = text.length();
        if (len < num) {
            return StringUtil.repeat(mix, len);
        }

        return StringUtil.join(
                text.substring(0, len - num),
                StringUtil.repeat(mix, num)
        );
    }
}
