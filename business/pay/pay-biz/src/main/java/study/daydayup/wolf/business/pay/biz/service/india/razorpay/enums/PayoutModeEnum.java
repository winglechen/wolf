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
public enum PayoutModeEnum implements CodeBasedEnum {

    IMPS(4, "IMPS"),
    RTGS(3, "RTGS"),
    NEFT(2, "NEFT"),
    UPI(1, "UPI"),
    ;

    private int code;
    private String name;

    PayoutModeEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }
}
