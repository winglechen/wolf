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
public enum ActionTypeEnum implements StatusEnum {
    ACL(4, "acl"),
    MENU(3, "menu"),
    GROUP(2, "group"),
    ACTION(1, "action")
    ;

    private int code;
    private String desc;
    ActionTypeEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
