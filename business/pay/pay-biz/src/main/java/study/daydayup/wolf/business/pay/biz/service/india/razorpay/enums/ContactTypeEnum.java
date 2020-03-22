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
public enum ContactTypeEnum implements CodeBasedEnum {
    VENDOR(4, "vendor"),
    CUSTOMER(3, "customer"),
    EMPLOYEE(2, "employee"),
    SELF(4, "self"),
    ;

    private int code;
    private String name;

    ContactTypeEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }
}
