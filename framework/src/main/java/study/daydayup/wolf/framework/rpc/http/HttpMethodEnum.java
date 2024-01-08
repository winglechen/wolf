package study.daydayup.wolf.framework.rpc.http;

import lombok.Getter;
import study.daydayup.wolf.common.lang.enums.CodeBasedEnum;
import study.daydayup.wolf.common.util.lang.EnumUtil;

/**
 * study.daydayup.wolf.framework.util.http
 *
 * @author Wingle
 * @since 2020/11/18 1:36 下午
 **/
@Getter
public enum HttpMethodEnum implements CodeBasedEnum {
    TRACE(8, "TRACE"),
    OPTIONS(7, "OPTIONS"),
    DELETE(6, "DELETE"),
    PATCH(5, "PATCH"),
    PUT(4, "PUT"),
    POST(3, "POST"),
    HEAD(2, "HEAD"),
    GET(1, "GET"),
    ;

    private final int code;
    private final String name;

    HttpMethodEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public static HttpMethodEnum parse(String name) {
        return EnumUtil.nameOf(name, HttpMethodEnum.class, true);
    }
}
