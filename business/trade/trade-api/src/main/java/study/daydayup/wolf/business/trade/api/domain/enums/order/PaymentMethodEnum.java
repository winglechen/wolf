package study.daydayup.wolf.business.trade.api.domain.enums.order;

import lombok.Getter;
import study.daydayup.wolf.common.lang.enums.CodeBasedEnum;

/**
 * study.daydayup.wolf.business.trade.api.domain.enums
 *
 * @author Wingle
 * @since 2019/10/5 6:55 PM
 **/
@Getter
@Deprecated
public enum PaymentMethodEnum implements CodeBasedEnum {
    MPURSE(101, "mpurse"),

    PAY_BY_COLLECTION(3, "自提"),
    PAY_BY_PROXY(2, "快递"),
    NO_NEED_TO_PAY(254, "无需支付")
    ;

    private int   code;
    private String desc;
    PaymentMethodEnum(int   code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
