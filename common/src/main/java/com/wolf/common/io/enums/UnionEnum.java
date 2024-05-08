package com.wolf.common.io.enums;

import lombok.Getter;
import com.wolf.common.lang.enums.CodeBasedEnum;

/**
 * com.wolf.common.io.db
 *
 * @author Wingle
 * @since 2019/11/25 9:10 下午
 **/
@Getter
public enum UnionEnum implements CodeBasedEnum {
    TOP(2, "top"),
    BOTTOM(1, "bottom")
    ;

    private final int code;
    private final String name;

    UnionEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }
}
