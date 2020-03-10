package study.daydayup.wolf.business.uc.api.profile.enums;

import lombok.Getter;
import study.daydayup.wolf.common.lang.enums.CodeBasedEnum;

/**
 * study.daydayup.wolf.business.trade.api.domain.enums
 * range(20 ~ 30)
 * @author Wingle
 * @since 2019/10/5 11:07 AM
 **/
@Getter
public enum CollectionTypeEnum implements CodeBasedEnum {
    ORG(2, "org"),
    GOODS(1, "goods"),
    ;

    private int code;
    private String name;
    CollectionTypeEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }
}
