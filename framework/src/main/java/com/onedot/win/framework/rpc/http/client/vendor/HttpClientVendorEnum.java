package com.onedot.win.framework.rpc.http.client.vendor;

import lombok.Getter;
import com.onedot.win.common.lang.enums.CodeBasedEnum;

/**
 * com.onedot.win.framework.util.http
 *
 * @author Wingle
 * @since 2020/11/18 1:36 下午
 **/
@Getter
public enum HttpClientVendorEnum implements CodeBasedEnum {
    OKHTTP(3, "okhttp"),
    NETTY_HTTPCLIENT(2, "netty"),
    JDK(1, "jdk"),
    ;

    private final int code;
    private final String name;

    HttpClientVendorEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }
}
