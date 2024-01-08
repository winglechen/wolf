package study.daydayup.wolf.common.lang.enums.trade;

import lombok.Getter;
import study.daydayup.wolf.common.lang.enums.CodeBasedEnum;

/**
 * study.daydayup.wolf.business.trade.api.domain.enums
 *
 * @author Wingle
 * @since 2019/10/9 6:30 下午
 **/
@Getter
@Deprecated
public enum TradePhaseEnum implements CodeBasedEnum {
    WITHDRAW_PHASE(9, "提现阶段"),
    DEPOSIT_PHASE(8, "充值阶段"),
    SETTLEMENT_PHASE(7, "结算阶段"),
    PAYMENT_PHASE(6, "支付阶段"),

    REFUND_PHASE(5, "售后阶段"),
    ORDER_PHASE(4, "订单阶段"),
    CONTRACT_PHASE(3, "合同阶段"),

    PAY_PAYOUT(2, "放款阶段"),
    UMP_PHASE(1, "营销阶段")
    ;

    private final int code;
    private final String name;
    TradePhaseEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }
}
