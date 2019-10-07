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
    BASIC_BUY(100, "默认交易流程"),
    GIFT(200, "送礼交易流程"),
    SECKILL(300, "秒杀交易流程"),
    PEERPAY(1000, "代付流程")
    ;

    private int code;
    private String desc;
    TradeTypeEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
