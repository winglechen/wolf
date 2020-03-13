package study.daydayup.wolf.business.uc.api.crm.manage.contact.enums;

import lombok.Getter;
import study.daydayup.wolf.common.lang.enums.CodeBasedEnum;

/**
 * study.daydayup.wolf.business.trade.api.domain.enums
 * range(20 ~ 30)
 * @author Wingle
 * @since 2019/10/5 11:07 AM
 **/
@Getter
public enum ContactMethodEnum implements CodeBasedEnum {
    SMS(3, "短信"),
    PHONE(2, "电话"),
    EMAIL(1, "邮件"),
    ;

    private int code;
    private String name;
    ContactMethodEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }
}
