package study.daydayup.wolf.business.trade.api.enums;

import lombok.Getter;
import study.daydayup.wolf.common.lang.enums.CodeBasedEnum;

/**
 * study.daydayup.wolf.business.trade.api.enums
 *
 * @author Wingle
 * @since 2019/10/4 9:11 PM
 **/
@Getter
public enum  TradeEventTypeEnum implements CodeBasedEnum {
    PAY(10, "支付事件"),
    SEND(20, "发货事件"),
    EXPIRED(30, "过期事件"),
    BUYER_CANCEL(40, "买家取消事件"),
    SELLER_CANCEL(50, "卖家取消事件"),
    COMPLETED(100, "订单完成事件")
    ;

    private int code;
    private String desc;

    TradeEventTypeEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
