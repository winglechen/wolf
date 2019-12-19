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
public enum RepayStrategyEnum implements CodeBasedEnum {
    UNKNOWN(3, "未知"),
    ONE_OFF(2, "一次性还款付息"),
    CONTRACT(1, "约定还款")
    ;

    private int code;
    private String desc;

    RepayStrategyEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
