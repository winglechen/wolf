package study.daydayup.wolf.common.lang.enums;

import lombok.Getter;

/**
 * study.daydayup.wolf.common.lang.enums
 * range(100~120)
 *
 * @author Wingle
 * @since 2019/9/29 4:51 PM
 **/
@Getter
public enum DurationStrategyEnum implements CodeBasedEnum {
    CLOSE_CLOSE(4, "close close"),
    CLOSE_OPEN(3, "close open"),
    OPEN_OPEN(2, "open open"),
    OPEN_CLOSE(1, "open close")
    ;

    private int code;
    private String desc;

    DurationStrategyEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
