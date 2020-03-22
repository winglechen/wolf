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
public enum AccountTypeEnum implements CodeBasedEnum {

    VPA(3, "vpa", "for UPI"),
    CARDS(2, "cards", "for NEFT or IMPS"),
    BANK_ACCOUNT(1, "bank_account", "for NEFT or IMPS"),
    ;

    private int code;
    private String name;
    private String desc;

    AccountTypeEnum(int code, String name, String desc) {
        this.code = code;
        this.name = name;
        this.desc = desc;
    }
}
