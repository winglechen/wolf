package study.daydayup.wolf.common.lang.enums.unit;

import lombok.Getter;
import study.daydayup.wolf.common.lang.enums.CodeBasedEnum;

/**
 * study.daydayup.wolf.common.lang.enums
 * range(1~100)
 *
 * @author Wingle
 * @since 2019/9/29 4:51 PM
 **/
@Getter
public enum UnitEnum implements CodeBasedEnum {
    NONE(1, "none unit")
    ;

    private int code;
    private String desc;

    UnitEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
