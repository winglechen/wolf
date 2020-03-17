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
public enum ContactMethodEnum implements CodeBasedEnum {
    SMS(3, "sms"),
    EMAIL(2, "email"),
    PHONE(1, "phone"),
    ;

    private int code;
    private String name;
    ContactMethodEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }
}
