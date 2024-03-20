package com.onedot.win.framework.layer.domain;

import lombok.Getter;
import com.onedot.win.common.lang.enums.CodeBasedEnum;

/**
 * com.onedot.win.common.lang.enums
 *
 * @author Wingle
 * @since 2019/9/29 4:51 PM
 **/
@Getter
public enum StateTypeEnum implements CodeBasedEnum {
    IMPORTANT(20, "IMPORTANT"),
    DEFAULT(10, "DEFAULT"),
    ;

    private final int code;
    private final String name;

    StateTypeEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }
}
