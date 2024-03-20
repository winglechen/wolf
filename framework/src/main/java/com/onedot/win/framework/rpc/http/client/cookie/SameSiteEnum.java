package com.onedot.win.framework.rpc.http.client.cookie;

import lombok.Getter;
import com.onedot.win.common.lang.enums.CodeBasedEnum;

/**
 * com.onedot.win.framework.util.http
 *
 * @author Wingle
 * @since 2020/11/18 1:36 下午
 **/
@Getter
public enum SameSiteEnum implements CodeBasedEnum {
    STRICT(30, "Strict"),
    LAX(20, "Lax"),
    NONE(10, "None"),
    ;

    private final int code;
    private final String name;

    SameSiteEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }
}
