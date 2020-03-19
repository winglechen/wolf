package study.daydayup.wolf.business.org.api.task.domain.enums.task;

import lombok.Getter;
import study.daydayup.wolf.common.lang.enums.CodeBasedEnum;

/**
 * study.daydayup.wolf.business.trade.api.domain.enums
 * range(20 ~ 30)
 * @author Wingle
 * @since 2019/10/5 11:07 AM
 **/
@Getter
public enum CollectionStateEnum implements CodeBasedEnum {
    FAILED(11, "failed"),
    PARTLY_FAILED(10, "partly failed"),
    FAILED_QUESTIONED(9, "failing questioned"),
    FAILING(8, "failing"),

    PAID(7, "paid"),
    PAID_QUESTIONED(6, "paying questioned"),
    PAYING(5, "paying"),

    PARTLY_PAID(4, "partly paid"),
    PARTLY_PAID_QUESTIONED(3, "partly paying questioned"),
    PARTLY_PAYING(2, "partly paying"),

    WAIT_TO_PAY(1, "wait to pay"),
    ;

    private int code;
    private String name;
    CollectionStateEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }
}
