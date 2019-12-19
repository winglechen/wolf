package study.daydayup.wolf.common.lang.enums.unit;

import lombok.Getter;
import study.daydayup.wolf.common.lang.enums.CodeBasedEnum;

/**
 * study.daydayup.wolf.common.lang.enums
 * range(140~150)
 *
 * @author Wingle
 * @since 2019/9/29 4:51 PM
 **/
@Getter
public enum RateEnum implements CodeBasedEnum {
    PER_MILLION(124, "百万分比"),
    PRE_HUNDRED_THOUSAND(123, "十万分比"),
    PRE_TEN_THOUSAND(122, "万分比"),
    PER_THOUSAND(121, "千分比"),
    PER_HUNDRED(120, "百分比")
    ;

    private int code;
    private String desc;

    RateEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
