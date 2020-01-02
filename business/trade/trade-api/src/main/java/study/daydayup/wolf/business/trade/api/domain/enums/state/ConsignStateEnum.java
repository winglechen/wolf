package study.daydayup.wolf.business.trade.api.domain.enums.state;

import lombok.Getter;
import study.daydayup.wolf.common.lang.enums.CodeBasedEnum;

/**
 * study.daydayup.wolf.business.trade.api.domain.enums
 *
 * @author Wingle
 * @since 2019/10/5 6:55 PM
 **/
@Getter
public enum ConsignStateEnum implements CodeBasedEnum {
    EXPIRED_SIGNED(50, "超时签收"),
    SIGNED(40, "已签收"),
    PART_SENDED(30, "部分已发货"),
    NO_NEED_TO_SEND(20, "无需发货"),
    SENDED(10, "已发货")
    ;

    private int   code;
    private String desc;
    ConsignStateEnum(int   code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
