package study.daydayup.wolf.business.uc.api.acl.enums;

import lombok.Getter;
import study.daydayup.wolf.business.uc.api.setting.enums.StatusEnum;

/**
 * study.daydayup.wolf.business.trade.api.domain.enums
 * range(1 ~ 20)
 * @author Wingle
 * @since 2019/10/5 11:07 AM
 **/
@Getter
public enum RoleTypeEnum implements StatusEnum {
    SUPER(10, "super"),
    ACCOUNT(3, "private role"),
    ORGANIZATION_STRUCTURE(2, "organization structure"),
    DEFAULT(1, "default")
    ;

    private int code;
    private String desc;
    RoleTypeEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
