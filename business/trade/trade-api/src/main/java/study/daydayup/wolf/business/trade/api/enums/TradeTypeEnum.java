package study.daydayup.wolf.business.trade.api.enums;

import lombok.Getter;
import study.daydayup.wolf.common.lang.enums.CodeBasedEnum;

/**
 * study.daydayup.wolf.business.trade.api.enums
 *
 * @author Wingle
 * @since 2019/10/5 11:07 AM
 **/
@Getter
public enum TradeTypeEnum implements CodeBasedEnum {
    GIFT(20, "送礼"),
    GIFT_RECEIVE(21, "收礼"),

    SECKILL(30, "秒杀"),

    PEERPAY(40, "代付"),
    PEERPAY_PAY(41, "代付支付"),

    GROUP_BUY(50, "团购"),
    GROUP_PARTICIPATE(51, "团购参与"),

    FENXIAO(60, "分销"),
    NORMAL(10, "普通")
    ;

    private int code;
    private String desc;
    TradeTypeEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
