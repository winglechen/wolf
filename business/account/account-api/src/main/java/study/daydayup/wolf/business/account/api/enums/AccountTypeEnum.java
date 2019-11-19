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
public enum AccountTypeEnum implements CodeBasedEnum {
    ORG(50, "组织"),
    ALIPAY(5, "alipay appid"),
    WECHAT(4, "wechat appid"),
    EMAIL(3, "email"),
    MOBILE(2, "mobile phone"),
    NAME(1, "name"),
    UNKNOWN(1, "unknown")
    ;

    private int code;
    private String desc;

    AccountTypeEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
