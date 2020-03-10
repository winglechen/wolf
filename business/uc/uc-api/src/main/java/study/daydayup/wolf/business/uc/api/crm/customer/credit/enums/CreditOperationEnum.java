package study.daydayup.wolf.business.uc.api.crm.customer.credit.enums;

import lombok.Getter;
import study.daydayup.wolf.business.uc.api.setting.enums.StatusEnum;

/**
 * study.daydayup.wolf.business.trade.api.domain.enums
 * range(20 ~ 30)
 * @author Wingle
 * @since 2019/10/5 11:07 AM
 **/
@Getter
public enum CreditOperationEnum implements StatusEnum {
    DEMOTION(2, "降额"),
    PROMOTION(1, "提额")
    ;

    private int code;
    private String name;
    CreditOperationEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }
}
