package study.daydayup.wolf.common.lang.enums.unit;

import lombok.Getter;
import study.daydayup.wolf.common.lang.enums.CodeBasedEnum;

/**
 * study.daydayup.wolf.common.lang.enums
 * range(120~140)
 *
 * @author Wingle
 * @since 2019/9/29 4:51 PM
 **/
@Getter
public enum InterestEnum implements CodeBasedEnum {
    COMPOUND_PER_YEAR(128, "compund insterst rate per year"),
    COMPOUND_PER_MONTH(127, "compund insterst rate per month"),
    COMPOUND_PER_WEEK(126, "compund insterst rate per week"),
    COMPOUND_PER_DAY(125, "compund insterst rate per day"),

    RATE_PER_YEAR(124, "rate per year"),
    RATE_PER_MONTH(123, "rate per month"),
    RATE_PER_WEEK(122, "rate per week"),
    RATE_PER_DAY(121, "rate per day"),

    FIXED_AMOUNT(121, "fixed amount"),
    FIXED_RATE(120, "fixed rate")
    ;

    private int code;
    private String desc;

    InterestEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
