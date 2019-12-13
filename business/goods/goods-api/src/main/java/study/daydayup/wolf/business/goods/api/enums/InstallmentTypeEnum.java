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
public enum InstallmentTypeEnum implements CodeBasedEnum {
    FAKE(20, "fake"),
    DEFAULT(10, "normal")
    ;

    private int code;
    private String desc;

    InstallmentTypeEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
