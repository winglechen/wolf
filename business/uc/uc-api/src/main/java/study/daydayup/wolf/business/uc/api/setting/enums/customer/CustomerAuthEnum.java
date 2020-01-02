package study.daydayup.wolf.business.uc.api.setting.enums.customer;

import lombok.Getter;
import study.daydayup.wolf.business.uc.api.setting.enums.StatusEnum;

/**
 * study.daydayup.wolf.business.trade.api.enums
 * range(20 ~ 30)
 * @author Wingle
 * @since 2019/10/5 11:07 AM
 **/
@Getter
public enum CustomerAuthEnum implements StatusEnum {
    AUTH_SUCCESS(29, "customer.auth.success"),

    AUTH(20, "customer.auth")
    ;

    private int code;
    private String desc;
    CustomerAuthEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
