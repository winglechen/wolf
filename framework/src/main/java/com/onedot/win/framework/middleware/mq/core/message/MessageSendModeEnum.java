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
public enum MessageSendModeEnum implements CodeBasedEnum {
    //ASYNC(20, "ASYNC"),
    SYNC(10, "SYNC"),
    ;

    private final int code;
    private final String name;

    MessageSendModeEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }
}
