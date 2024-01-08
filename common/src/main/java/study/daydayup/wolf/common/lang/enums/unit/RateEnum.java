package study.daydayup.wolf.common.lang.enums.unit;

import lombok.Getter;
import study.daydayup.wolf.common.lang.enums.CodeBasedEnum;
import study.daydayup.wolf.common.model.type.number.Rate;

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
    PER_MILLION(125, "百万分比", BigDecimal.valueOf(Rate.MILLION)),
    PRE_HUNDRED_THOUSAND(124, "十万分比", BigDecimal.valueOf(Rate.HUNDRED_THOUSAND)),
    PRE_TEN_THOUSAND(123, "万分比", BigDecimal.valueOf(Rate.TEN_THOUSAND)),
    PER_THOUSAND(122, "千分比", BigDecimal.valueOf(Rate.THOUSAND)),
    PER_HUNDRED(121, "百分比", BigDecimal.valueOf(Rate.HUNDRED)),
    PER_TEN(120, "十分比", BigDecimal.TEN)
    ;

    private final int code;
    private final String name;
    private final BigDecimal base;

    RateEnum(int code, String name, BigDecimal base) {
        this.code = code;
        this.name = name;
        this.base = base;
    }
}
