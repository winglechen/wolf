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
    private String name;

    JoinEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }
}
