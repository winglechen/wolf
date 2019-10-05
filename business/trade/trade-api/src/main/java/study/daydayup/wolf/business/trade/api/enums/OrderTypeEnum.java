package study.daydayup.wolf.business.trade.api.enums;

import lombok.Getter;
import study.daydayup.wolf.common.lang.enums.CodeBasedEnum;

/**
 * study.daydayup.wolf.business.trade.api.enums
 *
 * @author Wingle
 * @since 2019/10/5 6:55 PM
 **/
@Getter
public enum  OrderTypeEnum implements CodeBasedEnum {
    GIFT(20, "送礼订单"),
    GIFT_RECEIVE(30, "收礼订单"),
    PEERPAY(40, "代付订单"),
    GROUP_BUY(50, "团购订单"),
    FENXIAO(60, "分销订单"),
    NORMAL(10, "普通订单")
    ;

    private int code;
    private String desc;
    OrderTypeEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
