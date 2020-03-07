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
    FAIL(50, "任务失败"),
    TIMEOUT(40, "任务超时"),
    CANCEL(30, "任务取消"),
    DONE(20, "任务完成"),
    WORKING(10, "任务执行中")
    ;

    private int code;
    private String name;

    TaskStateEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }
}
