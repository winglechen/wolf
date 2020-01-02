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
public enum CustomerStatusGroupEnum {
    TRADE_TAG(TradeTagEnum.class),
    CUSTOMER_TAG(CustomerTagEnum.class),
    CUSTOMER_AUTH(CustomerAuthEnum.class),
    CUSTOMER_INFO(CustomerInfoEnum.class)
    ;


    private Class<? extends StatusEnum> group;
    CustomerStatusGroupEnum(Class<? extends StatusEnum> group) {
        this.group = group;
    }
}
