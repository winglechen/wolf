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
    LOAN_GOODS(50, "票务商品"),
    TICKET_GOODS(40, "票务商品"),
    VIRTUAL_GOODS(30, "虚拟商品"),
    IOT_GOODS(20, "IOT商品"),
    NORMAL_GOODS(10, "普通商品")
    ;

    private int code;
    private String desc;

    GoodsTypeEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
