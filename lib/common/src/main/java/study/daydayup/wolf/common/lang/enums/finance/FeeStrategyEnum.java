package study.daydayup.wolf.common.lang.enums.finance;

import lombok.Getter;
import study.daydayup.wolf.common.lang.enums.CodeBasedEnum;

/**
 * study.daydayup.wolf.common.lang.enums
 *
 * @author Wingle
 * @since 2019/9/29 4:51 PM
 **/
@Getter
public enum FeeStrategyEnum implements CodeBasedEnum {
    POST(3, "未知"),
    PRE(1, "提前付手续费")
    ;

    private int code;
    private String desc;

    FeeStrategyEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
