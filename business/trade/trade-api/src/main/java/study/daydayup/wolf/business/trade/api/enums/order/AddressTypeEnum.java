package study.daydayup.wolf.business.trade.api.enums.order;

import lombok.Getter;
import study.daydayup.wolf.common.lang.enums.CodeBasedEnum;

/**
 * study.daydayup.wolf.business.trade.api.enums
 *
 * @author Wingle
 * @since 2019/10/5 6:55 PM
 **/
@Getter
public enum AddressTypeEnum implements CodeBasedEnum {
    SELF_FETCH_ADDRESS(3, "自提点地址"),
    CONSIGN_ADDRESS(2, "收货地址"),
    NO_ADDRESS(1, "无地址")
    ;

    private Integer code;
    private String desc;
    AddressTypeEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
