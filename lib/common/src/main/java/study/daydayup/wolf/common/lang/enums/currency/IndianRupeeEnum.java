package study.daydayup.wolf.common.lang.enums.currency;

import lombok.Getter;
import study.daydayup.wolf.common.lang.enums.CodeBasedEnum;

/**
 * study.daydayup.wolf.model.enums
 *
 * @author Wingle
 * @since 2019/10/15 12:39 下午
 **/
@Getter
public enum IndianRupeeEnum implements CodeBasedEnum {
    PAISE(1009102, "paise"),
    RUPEE(1009101,"rupee")
    ;

    private int code;
    private String desc;

    IndianRupeeEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
