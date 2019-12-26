package study.daydayup.wolf.business.trade.api.enums.state;

import lombok.Getter;
import study.daydayup.wolf.common.lang.enums.CodeBasedEnum;

/**
 * study.daydayup.wolf.business.trade.api.enums
 *
 * @author Wingle
 * @since 2019/10/5 7:00 PM
 **/
@Getter
public enum ContractStateEnum implements CodeBasedEnum {
    COMPLETED(100, "合同完成"),
    CREATED(10, "合同创建")
    ;

    private int   code;
    private String desc;
    ContractStateEnum(int   code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
