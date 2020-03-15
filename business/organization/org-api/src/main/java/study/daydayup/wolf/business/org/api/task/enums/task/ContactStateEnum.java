package study.daydayup.wolf.business.org.api.task.enums.task;

import lombok.Getter;
import study.daydayup.wolf.common.lang.enums.CodeBasedEnum;

/**
 * study.daydayup.wolf.business.trade.api.domain.enums
 * range(20 ~ 30)
 * @author Wingle
 * @since 2019/10/5 11:07 AM
 **/
@Getter
public enum ContactStateEnum implements CodeBasedEnum {
    FAIL(2, "fail"),
    SUCCEED(1, "succeed"),
    ;

    private int code;
    private String name;
    ContactStateEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }
}
