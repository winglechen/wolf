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
public enum OrderEnum implements CodeBasedEnum {
    ASC(0, "ASC"),
    DESC(1, "DESC")
    ;

    private final int code;
    private final String name;

    OrderEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }
}
