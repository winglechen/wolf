package study.daydayup.wolf.business.trade.api.domain.enums.order;

import lombok.Getter;
import study.daydayup.wolf.common.lang.enums.CodeBasedEnum;

/**
 * study.daydayup.wolf.business.trade.api.domain.enums
 *
 * @author Wingle
 * @since 2019/10/5 6:55 PM
 **/
@Getter
public enum  OrderTypeEnum implements CodeBasedEnum {
    GIFT_CREATE(20, "送礼订单"),
    GIFT_RECEIVE(21, "收礼订单"),
    PEERPAY_CREATE(40, "代付订单"),
    PEERPAY_PAY(41, "代付支付单"),
    GROUP_BUY(50, "团购订单"),
    GROUP_PARTICIPATE(51, "团购参与单"),
    FENXIAO(60, "分销订单"),
    NORMAL(10, "普通订单")
    ;

    private int   code;
    private String desc;
    OrderTypeEnum(int   code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
