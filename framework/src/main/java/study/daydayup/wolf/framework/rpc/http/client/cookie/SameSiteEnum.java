package study.daydayup.wolf.framework.rpc.http.client.cookie;

import lombok.Getter;
import study.daydayup.wolf.common.lang.enums.CodeBasedEnum;

/**
 * study.daydayup.wolf.framework.util.http
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
