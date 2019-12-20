package study.daydayup.wolf.common.lang.enums.trade;

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
    CLEARING_PHASE(8, "清算阶段"),
    SETTLEMENT_PHASE(7, "结算阶段"),
    PAYMENT_PHASE(6, "支付阶段"),

    AFTER_SALE_PHASE(5, "售后阶段"),
    ORDER_PHASE(4, "订单阶段"),
    CONTRACT_PHASE(3, "合同阶段"),

    LICENSE_PHASE(2, "获取令牌阶段"),
    UMP_PHASE(1, "营销阶段")
    ;

    private int code;
    private String desc;
    TradePhaseEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
