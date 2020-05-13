package study.daydayup.wolf.business.union.app.http;

import lombok.Getter;
import study.daydayup.wolf.common.lang.enums.CodeBasedEnum;

/**
 * study.daydayup.wolf.business.union.app
 *
 * @author Wingle
 * @since 2020/5/11 4:43 下午
 **/
@Getter
public enum  ChannelEnum implements CodeBasedEnum {
    FACEBOOK(990002, "facebook"),
    GOOGLE(990001, "google"),
    ;

    private int code;
    private String name;

    ChannelEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }
}
