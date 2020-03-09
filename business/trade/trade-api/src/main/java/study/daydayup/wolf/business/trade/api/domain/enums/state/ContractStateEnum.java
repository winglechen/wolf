package study.daydayup.wolf.business.trade.api.domain.enums.state;

import lombok.Getter;
import study.daydayup.wolf.common.lang.enums.CodeBasedEnum;

/**
 * study.daydayup.wolf.business.trade.api.domain.enums
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
    private String name;
    ContractStateEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }
}
