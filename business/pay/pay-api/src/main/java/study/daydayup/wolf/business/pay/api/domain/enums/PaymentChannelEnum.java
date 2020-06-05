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
    DOKYPAY(120, "DOKYPAY"),
    CASEFREE_PAYOUT(111, "cash free payout"),
    CASEFREE(110, "cash free"),

    RAZORPAY_PAYOUT(101, "razor payout"),
    RAZORPAY(100, "razorpay"),

    WECHATPAY(2, "wechat pay"),
    ALIPAY(1, "alipay")
    ;

    private int code;
    private String name;

    PaymentChannelEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }
}
