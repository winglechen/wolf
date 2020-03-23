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
public enum PaymentLogTypeEnum implements CodeBasedEnum {
    PAYOUT_NOTIFY(21, "放款通知"),
    PAYOUT_REQUEST(20, "放款请求"),
    TRADE_NOTIFY(10, "私对私"),
    REFUND_REQUEST(8, "退款请求"),
    PAY_DUPLICATE(4, "重复支付"),
    PAY_RETURN(3, "支付通知"),
    PAY_NOTIFY(2, "支付通知"),
    PAY_REQUEST(1, "支付请求"),
    ;

    private int code;
    private String name;

    PaymentLogTypeEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }
}
