package study.daydayup.wolf.business.uc.api.setting.enums;

import lombok.Getter;
import study.daydayup.wolf.common.lang.enums.CodeBasedEnum;

/**
 * study.daydayup.wolf.business.trade.api.enums
 * range(1 ~ 20)
 * @author Wingle
 * @since 2019/10/5 11:07 AM
 **/
@Getter
public enum CustomerInfoEnum implements CodeBasedEnum {
    KYC(9, "customer.info.kyc"),
    VOTER(8, "customer.info.voter"),
    PASSPORT(7, "customer.info.passport"),
    DIVING_LICENSE(6, "customer.info.drivingLicense"),
    BANK_CARD(5, "customer.info.backCard"),
    LIVENESS(4, "customer.info.liveness"),
    PAN(3, "customer.info.pan"),
    AADHAAR(2, "customer.info.aadhaar"),
    BASIC_INFO(1, "customer.info.basicInfo")
    ;

    private int code;
    private String desc;
    CustomerInfoEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
