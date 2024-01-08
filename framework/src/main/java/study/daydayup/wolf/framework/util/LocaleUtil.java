package study.daydayup.wolf.framework.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import study.daydayup.wolf.common.util.lang.StringUtil;

import java.util.Locale;

/**
 * study.daydayup.wolf.framework.util
 *
 * @author Wingle
 * @since 2020/2/24 2:00 下午
 **/
@Component
public class LocaleUtil {
    private static MessageSource messageSource;

    @Autowired
    public LocaleUtil(MessageSource messageSource) {
        LocaleUtil.messageSource = messageSource;
        LocaleUtil.setLocale(Locale.US, true);
        LocaleUtil.setDefaultLocale(Locale.US);
    }

    public static String get(String key) {
        return get(key, null);
    }

    public static String get(String key, Object[] args) {
        return get(key, args, null);
    }

    public static String get(String key, Object[] args, Locale locale) {
        if (locale == null) {
            locale = LocaleUtil.getLocale();
        }

        try {
            return messageSource.getMessage(key, args, locale);
        } catch (Exception e) {
            return null;
        }
    }

    public static Locale getLocale() {
        return LocaleContextHolder.getLocale();
    }

    public static String getLocaleString() {
        Locale locale = getLocale();

        StringBuilder result = new StringBuilder();
        String lang = locale.getLanguage();
        if (StringUtil.isEmpty(lang)) {
            return null;
        }
        result.append(lang);

        String country = locale.getCountry();
        if (StringUtil.notEmpty(country)) {
            result.append(StringUtil.UNDERSCORE)
                    .append(country);
        }

        return result.toString();
    }

    public static void setLocaleString(String str) {
        if (StringUtil.isEmpty(str)) {
            return;
        }

        String lang = "", country = "";
        if (!str.contains(StringUtil.UNDERSCORE)) {
            lang = str.trim();
        } else {
            String[] sArr = StringUtil.split(str, StringUtil.UNDERSCORE);
            if (null == sArr || 2 != sArr.length) {
                return;
            }

            if (StringUtil.isEmpty(sArr[0], true)) {
                return;
            }
            lang = sArr[0].trim();

            if (StringUtil.notEmpty(sArr[1], true)) {
                country = sArr[1].trim();
            }
        }

        Locale locale = new Locale(lang, country);
        setLocale(locale);
    }

    public static void setDefaultLocale(Locale locale) {
        LocaleContextHolder.setDefaultLocale(locale);
    }

    public static void setLocale(Locale locale) {
        LocaleContextHolder.setLocale(locale);
    }

    public static void setLocale(Locale locale, boolean inheritable) {
        LocaleContextHolder.setLocale(locale, inheritable);
    }



}
