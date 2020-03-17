package study.daydayup.wolf.business.org.api.task.domain.enums.task;

import lombok.Getter;
import study.daydayup.wolf.common.lang.enums.CodeBasedEnum;

/**
 * study.daydayup.wolf.business.trade.api.domain.enums
 * range(20 ~ 30)
 * @author Wingle
 * @since 2019/10/5 11:07 AM
 **/
@Getter
public enum ContactPersonEnum implements CodeBasedEnum {
    COLLEAGUE(3, "colleague"),
    RELATIVE(2, "relative"),
    SELF(1, "self"),
    ;

    private int code;
    private String name;
    ContactPersonEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }
}
