package study.daydayup.wolf.business.trade.api.enums.state;

import lombok.Getter;
import study.daydayup.wolf.common.lang.enums.CodeBasedEnum;

/**
 * study.daydayup.wolf.business.trade.api.enums
 *
 * @author Wingle
 * @since 2019/10/5 6:55 PM
 **/
@Getter
public enum AfterSaleStateEnum implements CodeBasedEnum {
    REFUND(30, "退款"),
    REQUEST_HANDLING(20, "维权处理中"),
    REQUEST_SAFEGUARDING(10, "发起维权")
    ;

    private int code;
    private String desc;
    AfterSaleStateEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
