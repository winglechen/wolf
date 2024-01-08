package study.daydayup.wolf.framework.middleware.mq.core.consumer;

import lombok.Getter;
import study.daydayup.wolf.common.lang.enums.CodeBasedEnum;

/**
 * study.daydayup.wolf.common.lang.enums
 *
 * @author Wingle
 * @since 2019/9/29 4:51 PM
 **/
@Getter
public enum ConsumeStateEnum implements CodeBasedEnum {
    LATER(60, "LATER"),
    TIMEOUT(50, "TIMEOUT"),

    FAILURE(20, "FAILURE"),
    SUCCESS(10, "SUCCESS"),
    ;

    private final int code;
    private final String name;

    ConsumeStateEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }
}
