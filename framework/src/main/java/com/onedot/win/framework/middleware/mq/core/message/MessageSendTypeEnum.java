package com.onedot.win.framework.middleware.mq.core.message;

import lombok.Getter;
import com.onedot.win.common.lang.enums.CodeBasedEnum;

/**
 * com.onedot.win.common.lang.enums
 *
 * @author Wingle
 * @since 2019/9/29 4:51 PM
 **/
@Getter
public enum MessageSendTypeEnum implements CodeBasedEnum {
    ONE_WAY(70, "ONE_WAY"),
    TRANSACTION(60, "TRANSACTION"),
    BROADCASTING(50, "BROADCASTING"),
    ORDER(40, "ORDER"),
    SCHEDULE(30, "SCHEDULE"),
    ;

    private final int code;
    private final String name;

    MessageSendTypeEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }
}
