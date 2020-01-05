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
public enum GoodsStateEnum implements CodeBasedEnum {
    UNSALABLE(2, "下架"),
    SALABLE(1, "上架"),
    DEFAULT(0, "默认")
    ;

    private int code;
    private String desc;

    GoodsStateEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
