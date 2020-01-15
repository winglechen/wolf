package study.daydayup.wolf.common.lang.enums.unit;

import lombok.Getter;
import study.daydayup.wolf.common.lang.enums.CodeBasedEnum;

/**
 * study.daydayup.wolf.common.lang.enums
 * range(100~119)
 *
 * @author Wingle
 * @since 2019/9/29 4:51 PM
 **/
@Getter
public enum PeriodEnum implements CodeBasedEnum {
    YEARS(114, "years"),
    MONTHS(113, "months"),
    WEEKS(112, "weeks"),
    DAYS(111, "days"),

    HOURS(110, "hours"),
    MINUTES(109, "minutes"),
    SECONDS(108, "seconds")
    ;

    private int code;
    private String desc;

    PeriodEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
