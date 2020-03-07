package study.daydayup.wolf.business.org.api.staff.acl.enums;

import lombok.Getter;
import study.daydayup.wolf.common.lang.enums.CodeBasedEnum;

/**
 * study.daydayup.wolf.business.trade.api.domain.enums
 * range(1 ~ 20)
 * @author Wingle
 * @since 2019/10/5 11:07 AM
 **/
@Getter
public enum RoleTypeEnum implements CodeBasedEnum {
    SUPER(10, "super"),
    ACCOUNT(3, "account"),
    DEPARTMENT(2, "department"),
    DEFAULT(1, "default")
    ;

    private int code;
    private String name;
    RoleTypeEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }
}
