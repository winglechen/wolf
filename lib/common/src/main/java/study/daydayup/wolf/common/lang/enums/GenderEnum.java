package study.daydayup.wolf.common.lang.enums;

import lombok.Getter;

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
    private String name;

    GenderEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }
}
