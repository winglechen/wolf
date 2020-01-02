package study.daydayup.wolf.business.uc.api.setting.enums.customer;

import lombok.Getter;
import study.daydayup.wolf.business.uc.api.setting.enums.StatusEnum;

/**
 * study.daydayup.wolf.business.trade.api.enums
 * range( 30 ~ 50 )
 * @author Wingle
 * @since 2019/10/5 11:07 AM
 **/
@Getter
public enum TradeTagEnum implements StatusEnum {
    LOAN_REPAID_TIMEOUT(33, "trade.loan.repaid.timeout"),
    LOAN_REFUSED_TIMEOUT(32, "trade.loan.refused.timeout"),
    LOAN_LOANING(31, "trade.loan.loaning"),
    FIRST_TRADE(30, "trade.first")
    ;

    private int code;
    private String desc;
    TradeTagEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
