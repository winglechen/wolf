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
    AFTER_SALE_PHASE(60, "售后阶段"),
    ORDER_PHASE(50, "订单阶段"),
    CONTRACT_PHASE(40, "合同阶段"),
    PREVIEW_PHASE(30, "交易预览阶段"),
    TOKEN_PHASE(20, "获取令牌阶段"),
    UMP_PHASE(10, "营销阶段")
    ;

    private int code;
    private String desc;
    TradePhaseEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
