package study.daydayup.wolf.common.lang.enums;

import lombok.Getter;
import study.daydayup.wolf.common.lang.exception.enums.EnumNotFoundException;

/**
 * study.daydayup.wolf.common.lang.enums
 *
 * @author Wingle
 * @since 2019/9/29 4:51 PM
 **/
@Getter
public enum GenderEnum implements CodeBasedEnum {
    UNKNOWN(0, "未知"),
    MALE(1, "男"),
    FEMALE(2, "女")
    ;

    private int code;
    private String desc;

    GenderEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
