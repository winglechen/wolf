package study.daydayup.wolf.business.uc.api.setting.enums;

import lombok.Getter;
import study.daydayup.wolf.common.lang.enums.CodeBasedEnum;

/**
 * study.daydayup.wolf.business.trade.api.enums
 * range( 50 ~ 100 )
 * @author Wingle
 * @since 2019/10/5 11:07 AM
 **/
@Getter
public enum CustomerTagEnum implements CodeBasedEnum {

    BLOCK_LIST(50, "customer.tag.blockList")
    ;

    private int code;
    private String desc;
    CustomerTagEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
