package study.daydayup.wolf.business.account.api.enums;

import lombok.Getter;
import study.daydayup.wolf.common.lang.enums.CodeBasedEnum;

/**
 * study.daydayup.wolf.business.goods.api.enums
 *
 * @author Wingle
 * @since 2019/10/18 5:44 下午
 **/
@Getter
public enum OpenAppTypeEnum implements CodeBasedEnum {
    PRODUCTION(2, "production"),
    TEST(1, "test"),
    UNKNOWN(0, "unknown")
    ;

    private final int code;
    private final String name;

    OpenAppTypeEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }
}
