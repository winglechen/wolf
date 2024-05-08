package com.wolf.framework.middleware.binlog;

import lombok.Getter;
import com.wolf.common.lang.enums.CodeBasedEnum;

/**
 * @author weixing
 * @since 2022/8/22 10:31
 */
@Getter
public enum BinlogConsumeTypeEnum implements CodeBasedEnum {
    ORDERED(1, "ordered"),
    GROUP_BATCHED(2, "group batched")
    ;

    private final int code;
    private final String name;

    BinlogConsumeTypeEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }
}
