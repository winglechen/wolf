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
    LOAN(50, "票务商品"),
    TICKET(40, "票务商品"),
    VIRTUAL(30, "虚拟商品"),
    IOT(20, "IOT商品"),
    NORMAL(10, "普通商品")
    ;

    private Integer code;
    private String desc;

    GoodsTypeEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
