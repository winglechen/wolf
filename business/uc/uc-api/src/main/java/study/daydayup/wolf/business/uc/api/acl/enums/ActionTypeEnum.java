package study.daydayup.wolf.business.uc.api.acl.enums;

import lombok.Getter;
import study.daydayup.wolf.common.lang.enums.CodeBasedEnum;

/**
 * study.daydayup.wolf.business.trade.api.domain.enums
 * range(1 ~ 20)
 * @author Wingle
 * @since 2019/10/5 11:07 AM
 **/
@Getter
public enum ActionTypeEnum implements CodeBasedEnum {
    GROUP(2, "group"),
    ACTION(1, "action")
    ;

    private int code;
    private String name;
    ActionTypeEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }
}
