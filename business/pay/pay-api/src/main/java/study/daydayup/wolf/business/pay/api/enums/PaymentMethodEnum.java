package study.daydayup.wolf.business.pay.api.enums;

import lombok.Getter;
import study.daydayup.wolf.common.lang.enums.CodeBasedEnum;

/**
 * study.daydayup.wolf.common.lang.enums
 *
 * @author Wingle
 * @since 2019/9/29 4:51 PM
 **/
@Getter
public enum PaymentMethodEnum implements CodeBasedEnum {

    RAZORPAY(101, "razorpay"),

    WECHAT_PAY(2, "wechat pay"),
    ALIPAY(1, "alipay")
    ;

    private int code;
    private String desc;

    PaymentMethodEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
