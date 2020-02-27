package study.daydayup.wolf.demo.my.starter.base;

import org.springframework.util.StringUtils;

import java.util.Locale;

/**
 * study.daydayup.wolf.demo.my.starter.base
 *
 * @author Wingle
 * @since 2020/2/27 5:12 下午
 **/
public class LocaleDemo {
    public static void main(String[] args) {
        String lang = "zh-CN";
        Locale locale = StringUtils.parseLocale(lang);

        System.out.println(locale);
    }
}
