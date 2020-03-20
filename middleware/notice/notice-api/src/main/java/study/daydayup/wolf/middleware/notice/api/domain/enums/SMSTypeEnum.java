package study.daydayup.wolf.middleware.notice.api.domain.enums;

import lombok.Getter;
import study.daydayup.wolf.common.lang.enums.CodeBasedEnum;

/**
 * study.daydayup.wolf.business.trade.api.domain.enums
 * range(20 ~ 30)
 * @author Wingle
 * @since 2019/10/5 11:07 AM
 **/
@Getter
public enum SMSTypeEnum implements CodeBasedEnum {
    TRADE(2, "notice"),
    NOTICE(2, "notice"),
    CAPTCHA(1, "captcha")
    ;

    private int code;
    private String name;
    SMSTypeEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }
}
