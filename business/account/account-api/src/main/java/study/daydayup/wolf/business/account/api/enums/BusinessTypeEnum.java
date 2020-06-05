package study.daydayup.wolf.business.account.api.enums;

import lombok.Getter;
import study.daydayup.wolf.common.lang.enums.CodeBasedEnum;

/**
 * study.daydayup.wolf.business.goods.api.enums
 *
 * @author Wingle
 * @since 2019/10/18 5:44 下午
 **/
@Getter
public enum BusinessTypeEnum implements CodeBasedEnum {
    AUDIT_FEE(4, "audit fee"),
    REPAY(3, "repay"),
    PAYOUT(2, "payout"),
    E_COMMERCE(1, "e-commerce")
    ;

    private int code;
    private String name;

    BusinessTypeEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }
}
