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
public enum SkuTypeEnum implements CodeBasedEnum {
    TREE(40, "数字库存"),
    TWO_LEVEL(30, "是否库存"),
    ONE_LEVEl(20, "上下架库存"),
    NONE(10, "无库存")
    ;

    private int code;
    private String name;

    SkuTypeEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }
}
