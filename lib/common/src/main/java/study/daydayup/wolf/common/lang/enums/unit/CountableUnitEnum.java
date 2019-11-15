package study.daydayup.wolf.common.lang.enums.unit;

import lombok.Getter;
import study.daydayup.wolf.common.lang.enums.CodeBasedEnum;

/**
 * study.daydayup.wolf.common.lang.enums
 * range(1000~2000)
 *
 * @author Wingle
 * @since 2019/9/29 4:51 PM
 **/
@Getter
public enum CountableUnitEnum implements CodeBasedEnum {
    QUANTITY(101, "quantity")
    ;

    private int code;
    private String desc;

    CountableUnitEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
