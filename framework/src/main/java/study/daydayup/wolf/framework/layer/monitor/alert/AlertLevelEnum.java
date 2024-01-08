package study.daydayup.wolf.framework.layer.monitor.alert;

import lombok.Getter;
import study.daydayup.wolf.common.lang.enums.CodeBasedEnum;

/**
 * study.daydayup.wolf.common.lang.enums
 *
 * @author Wingle
 * @since 2019/9/29 4:51 PM
 **/
@Getter
public enum AlertLevelEnum implements CodeBasedEnum {
    WARNING(10, "SYNC"),
    ;

    private final int code;
    private final String name;

    AlertLevelEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }
}
