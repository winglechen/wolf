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
public enum OrgTypeEnum implements CodeBasedEnum {
    PLATFORM(2, "platform"),
    SHOP(1, "shop")
    ;

    private int code;
    private String desc;

    OrgTypeEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
