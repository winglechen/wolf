package study.daydayup.wolf.common.lang.enums.currency;

import lombok.Getter;
import study.daydayup.wolf.common.lang.enums.CodeBasedEnum;

/**
 * study.daydayup.wolf.model.enums
 *
 * @author Wingle
 * @since 2019/10/15 12:39 下午
 **/
@Getter
public enum RMBEnum implements CodeBasedEnum {
    SI(6, "丝"),
    HAO(5, "毫"),
    LI(4, "厘"),
    FEN(3, "分"),
    JIAO(2, "角"),
    YUAN(1,"元")
    ;

    private int code;
    private String desc;

    RMBEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
