package study.daydayup.wolf.business.goods.api.enums;

import lombok.Getter;
import study.daydayup.wolf.common.lang.enums.CodeBasedEnum;

/**
 * study.daydayup.wolf.business.goods.api.enums
 *
 * @author Wingle
 * @since 2019/10/18 5:44 下午
 **/
@Getter
public enum StockTypeEnum implements CodeBasedEnum {
    TIMER_STOCK(60, "时段库存"),
    TOKEN_STOCK(50, "令牌库存"),
    NUMBER_STOCK(40, "数字库存"),
    BOOL_STOCK(30, "是否库存"),
    SOLD_STATE_STOCK(20, "上下架库存"),
    NO_STOCK(10, "无库存")
    ;

    private Integer code;
    private String desc;

    StockTypeEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
