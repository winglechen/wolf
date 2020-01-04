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
    ASC(0, "asc"),
    DESC(1, "desc")
    ;

    private int code;
    private String desc;

    OrderEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
