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
    THOUSANDTH_PAISE(1009106, "1/1000 paise"),
    HUNDREDTH_PAISE(1009105, "1/100 paise"),
    TENTH_PAISE(1009104, "1/10 paise"),
    PAISE(1009103, "paise"),
    TEN_PAISE(1009102, "ten paise"),
    RUPEE(1009101,"rupee")
    ;

    private int code;
    private String name;

    IndianRupeeEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }
}
