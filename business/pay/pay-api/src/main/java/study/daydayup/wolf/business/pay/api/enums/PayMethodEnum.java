package study.daydayup.wolf.business.pay.api.enums;

import lombok.Getter;
import study.daydayup.wolf.common.lang.enums.CodeBasedEnum;

/**
 * study.daydayup.wolf.common.lang.enums
 *
 * @author Wingle
 * @since 2019/9/29 4:51 PM
 **/
@Getter
public enum PayMethodEnum implements CodeBasedEnum {
    WECHAT(2, "wechat pay"),
    ALIPAY(1, "alipay"),
    UNKNOWN(0, "未知")
    ;

    private int code;
    private String desc;

    PayMethodEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
