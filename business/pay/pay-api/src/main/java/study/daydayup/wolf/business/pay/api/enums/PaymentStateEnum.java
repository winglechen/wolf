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
public enum PaymentStateEnum implements CodeBasedEnum {

    PARTIAL_REFUNDED(6, "部分退款"),
    REFUNDED(5, "已退款"),
    PAID(4, "支付成功"),
    FAIL(3, "支付失败"),
    PAYING(3, "支付中"),
    WAIT_TO_PAY(1, "待支付"),
    ;

    private int code;
    private String name;

    PaymentStateEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }
}
