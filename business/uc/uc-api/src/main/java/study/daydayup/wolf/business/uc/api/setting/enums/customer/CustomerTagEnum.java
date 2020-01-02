package study.daydayup.wolf.business.uc.api.setting.enums.customer;

import lombok.Getter;
import study.daydayup.wolf.business.uc.api.setting.enums.StatusEnum;

/**
 * study.daydayup.wolf.business.trade.api.domain.enums
 * range( 50 ~ 100 )
 * @author Wingle
 * @since 2019/10/5 11:07 AM
 **/
@Getter
public enum CustomerTagEnum implements StatusEnum {
    IS_STAFF(51, "customer.tag.isStaff"),
    IN_BLOCK_LIST(50, "customer.tag.blockList")
    ;

    private int code;
    private String desc;
    CustomerTagEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
