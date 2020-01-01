package study.daydayup.wolf.business.trade.api.util;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * study.daydayup.wolf.business.trade.api.util
 *
 * @author Wingle
 * @since 2019/12/23 11:55 下午
 **/
@Component
public class LocaleUtil {
    @Resource
    private MessageSource messageSource;

    public LocaleUtil(MessageSource source) {
        messageSource = source;
    }

    public String get(String key) {
        try {
            return messageSource.getMessage(key, null, LocaleContextHolder.getLocale());
        } catch (Exception e) {
            return null;
        }
    }
}
