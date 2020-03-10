package study.daydayup.wolf.business.org.api.task.enums;

import lombok.Getter;
import study.daydayup.wolf.common.lang.enums.CodeBasedEnum;

/**
 * study.daydayup.wolf.business.trade.api.domain.enums
 * range(20 ~ 30)
 * @author Wingle
 * @since 2019/10/5 11:07 AM
 **/
@Getter
public enum TaskTypeEnum implements CodeBasedEnum {
    ORG(2, "org"),
    GOODS(1, "goods"),
    ;

    private int code;
    private String name;
    TaskTypeEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }
}
