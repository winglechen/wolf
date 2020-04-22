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
public enum GoodsTypeEnum implements CodeBasedEnum {

    PRE_LOAN_FEE(51, "前置贷超"),
    LOAN_MARKET(52, "贷超商品"),
    LOAN(50, "贷款商品"),

    TICKET(40, "票务商品"),
    VIRTUAL(30, "虚拟商品"),
    IOT(20, "IOT商品"),
    NORMAL(10, "普通商品")
    ;

    private int code;
    private String name;

    GoodsTypeEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }
}
