package study.daydayup.wolf.business.uc.setting.api.enums.customer;

import lombok.Getter;
import study.daydayup.wolf.business.uc.setting.api.enums.StatusEnum;

/**
 * study.daydayup.wolf.business.trade.api.domain.enums
 * range( 50 ~ 100 )
 * @author Wingle
 * @since 2019/10/5 11:07 AM
 **/
@Getter
public enum CustomerTagEnum implements StatusEnum {
    IS_STAFF(51, "customer.tag.staff"),
    IN_BLOCK_LIST(50, "customer.tag.block")
    ;

    private int code;
    private String name;
    CustomerTagEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }
}
