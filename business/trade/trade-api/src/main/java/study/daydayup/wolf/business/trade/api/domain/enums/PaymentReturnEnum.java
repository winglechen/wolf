package study.daydayup.wolf.business.trade.api.domain.enums;

import lombok.Getter;
import study.daydayup.wolf.common.lang.enums.CodeBasedEnum;

/**
 * study.daydayup.wolf.common.lang.enums
 *
 * @author Wingle
 * @since 2019/9/29 4:51 PM
 **/
@Getter
public enum PaymentReturnEnum implements CodeBasedEnum {
    ERROR(20, "timeout"),
    TIMEOUT(10, "timeout"),
    USELESS(5, "useless notification"),
    PARSE_ERROR(4, "parse error"),
    DUPLICATE(3, "duplicate"),
    FAIL(2, "fail"),
    SUCCESS(1, "success"),
    ;

    private int code;
    private String name;

    PaymentReturnEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }
}
