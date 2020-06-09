package study.daydayup.wolf.business.pay.api.domain.enums;

import lombok.Getter;
import study.daydayup.wolf.common.lang.enums.CodeBasedEnum;

/**
 * study.daydayup.wolf.common.lang.enums
 *
 * @author Wingle
 * @since 2019/9/29 4:51 PM
 **/
@Getter
public enum PaymentChannelEnum implements CodeBasedEnum {
    DOKYPAY(120, "dokypay"),

    CASEFREE_PAYOUT(111, "cashfreePayout"),
    CASEFREE(110, "cashfree"),

    RAZORPAY_PAYOUT(101, "razorpayPayout"),
    RAZORPAY(100, "razorpay"),

    WECHATPAY(2, "wechatpay"),
    ALIPAY(1, "alipay")
    ;

    private final int code;
    private final String name;

    PaymentChannelEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }
}
