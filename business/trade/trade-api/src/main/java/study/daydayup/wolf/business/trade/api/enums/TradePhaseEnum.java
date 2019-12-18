package study.daydayup.wolf.business.trade.api.enums;

import lombok.Getter;
import study.daydayup.wolf.common.lang.enums.CodeBasedEnum;

/**
 * study.daydayup.wolf.business.trade.api.enums
 *
 * @author Wingle
 * @since 2019/10/9 6:30 下午
 **/
@Getter
public enum TradePhaseEnum implements CodeBasedEnum {
    CLEARING_PHASE(120, "清算阶段"),
    SETTLEMENT_PHASE(110, "结算阶段"),
    PAYMENT_PHASE(100, "支付阶段"),

    AFTER_SALE_PHASE(70, "售后阶段"),

    CONFIRM_PHASE(40, "确认阶段"),
    PREVIEW_PHASE(30, "预览阶段"),

    LICENSE_PHASE(20, "获取令牌阶段"),
    UMP_PHASE(10, "营销阶段")
    ;

    private int code;
    private String desc;
    TradePhaseEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
