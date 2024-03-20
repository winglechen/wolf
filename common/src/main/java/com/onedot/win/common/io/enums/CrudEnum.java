package com.onedot.win.common.io.enums;

import lombok.Getter;
import com.onedot.win.common.lang.enums.CodeBasedEnum;

/**
 * com.onedot.win.common.io.db
 *
 * @author Wingle
 * @since 2019/11/25 9:10 下午
 **/
@Getter
public enum CrudEnum implements CodeBasedEnum {
    DELETE(4, "delete"),
    SELECT(3, "select"),
    UPDATE(2, "update"),
    INSERT(1, "insert")
    ;

    private final int code;
    private final String name;

    CrudEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }
}
