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
    INSTALLMENT(3, "后付待续费"),
    POST(2, "后付待续费"),
    PRE(1, "提前付手续费")
    ;

    private int code;
    private String name;

    FeeStrategyEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }
}
