package study.daydayup.wolf.business.trade.api.enums.state;

import lombok.Getter;
import study.daydayup.wolf.common.lang.enums.CodeBasedEnum;

/**
 * study.daydayup.wolf.business.trade.api.enums
 *
 * @author Wingle
 * @since 2019/10/5 6:55 PM
 **/
@Getter
public enum OrderStateEnum implements CodeBasedEnum {
    CLOSED(110, "收礼订单"),
    COMPLETED(100, "代付订单"),
    SEND(30, "团购订单"),
    PAID(20, "分销订单"),
    WAIT_TO_PAY(10, "普通订单")
    ;

    private int code;
    private String desc;
    OrderStateEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
