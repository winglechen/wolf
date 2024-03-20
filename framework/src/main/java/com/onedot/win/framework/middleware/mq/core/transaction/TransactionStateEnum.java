package com.onedot.win.framework.middleware.mq.core.transaction;

import lombok.Getter;
import com.onedot.win.common.lang.enums.CodeBasedEnum;

/**
 * com.onedot.win.common.lang.enums
 *
 * @author Wingle
 * @since 2019/9/29 4:51 PM
 **/
@Getter
public enum TransactionStateEnum implements CodeBasedEnum {
    TIMEOUT(60, "UNKNOWN"),
    RETRY(50, "RETRY"),

    ROLLBACK(30, "ROLLBACK"),
    COMMIT(20, "COMMIT"),

    UNKNOWN(10, "UNKNOWN"),
    ;

    private final int code;
    private final String name;

    TransactionStateEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }
}
