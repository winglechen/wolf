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
public enum DurationEnum implements CodeBasedEnum {
    SECONDS(110, "seconds"),
    MINUTES(111, "minutes"),
    HOURS(112, "hours"),
    YEARS(104, "years"),
    MONTHS(103, "months"),
    WEEKS(102, "weeks"),
    DAYS(101, "days")
    ;

    private int code;
    private String desc;

    DurationEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
