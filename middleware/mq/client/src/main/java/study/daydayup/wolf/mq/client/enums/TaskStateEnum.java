package study.daydayup.wolf.mq.client.enums;

import lombok.Getter;
import study.daydayup.wolf.common.lang.enums.CodeBasedEnum;

/**
 * study.daydayup.wolf.common.lang.enums
 *
 * @author Wingle
 * @since 2019/9/29 4:51 PM
 **/

@Getter
public enum TaskStateEnum implements CodeBasedEnum {
    UNKNOWN(0, "未知"),
    FAIL(40, "任务失败"),
    TIMEOUT(30, "任务超时"),
    DONE(20, "任务完成"),
    WORKING(10, "任务执行中")
    ;

    private int code;
    private String desc;

    TaskStateEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
