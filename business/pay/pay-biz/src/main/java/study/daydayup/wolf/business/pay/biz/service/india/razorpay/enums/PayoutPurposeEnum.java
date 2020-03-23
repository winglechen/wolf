package study.daydayup.wolf.business.pay.biz.service.india.razorpay.enums;

import lombok.Getter;
import study.daydayup.wolf.common.lang.enums.CodeBasedEnum;

/**
 * study.daydayup.wolf.common.lang.enums
 *
 * @author Wingle
 * @since 2019/9/29 4:51 PM
 **/
@Getter
public enum PayoutPurposeEnum implements CodeBasedEnum {

    REFUND(2, "refund"),
    PAYOUT(1, "payout"),
    ;

    private int code;
    private String name;

    PayoutPurposeEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }
}
