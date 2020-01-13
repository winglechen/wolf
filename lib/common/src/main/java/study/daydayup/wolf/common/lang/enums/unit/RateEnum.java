package study.daydayup.wolf.common.lang.enums.unit;

import lombok.Getter;
import study.daydayup.wolf.common.lang.enums.CodeBasedEnum;

import java.math.BigDecimal;

/**
 * study.daydayup.wolf.common.lang.enums
 * range(140~150)
 *
 * @author Wingle
 * @since 2019/9/29 4:51 PM
 **/
@Getter
public enum RateEnum implements CodeBasedEnum {
    PER_MILLION(124, "百万分比", BigDecimal.valueOf(1000000)),
    PRE_HUNDRED_THOUSAND(123, "十万分比", BigDecimal.valueOf(100000)),
    PRE_TEN_THOUSAND(122, "万分比", BigDecimal.valueOf(10000)),
    PER_THOUSAND(121, "千分比", BigDecimal.valueOf(1000)),
    PER_HUNDRED(120, "百分比", BigDecimal.valueOf(100))
    ;

    private int code;
    private String desc;
    private BigDecimal base;

    RateEnum(int code, String desc, BigDecimal base) {
        this.code = code;
        this.desc = desc;
        this.base = base;
    }
}
