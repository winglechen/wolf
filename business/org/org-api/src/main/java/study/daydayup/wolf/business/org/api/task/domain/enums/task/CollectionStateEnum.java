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
    FAILING(9, "failing"),

    PAID(8, "paid"),
    PAYING(7, "paying"),

    FAILED_QUESTIONED(6, "failing questioned"),
    PAID_QUESTIONED(5, "paying questioned"),
    PARTLY_PAID_QUESTIONED(4, "partly paying questioned"),

    PARTLY_PAID(3, "partly paid"),
    PARTLY_PAYING(2, "partly paying"),


    WAIT_TO_PAY(1, "partly paying"),
    ;

    private int code;
    private String name;
    CollectionStateEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }
}
