package study.daydayup.wolf.common.io.enums;

import lombok.Getter;
import study.daydayup.wolf.common.lang.enums.CodeBasedEnum;

/**
 * study.daydayup.wolf.common.io.db
 *
 * @author Wingle
 * @since 2019/11/25 9:10 下午
 **/
@Getter
public enum JoinEnum implements CodeBasedEnum {
    LEFT(2, "left"),
    RIGHT(1, "right")
    ;

    private int code;
    private String desc;

    JoinEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
