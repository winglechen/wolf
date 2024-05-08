package com.wolf.framework.middleware.ratelimit;

import lombok.Data;
import com.wolf.framework.layer.api.ModelV1;

import java.time.LocalDateTime;

/**
 * com.wolf.framework.middleware.ratelimit
 *
 * @author Wingle
 * @since 2020/10/16 3:22 下午
 **/
@Data
public class RequestCounter implements ModelV1 {
    private Integer lastSecondCount;
    private Integer lastMinuteCount;
    private Integer lastHourCount;

    private Integer lastDayCount;
    private Integer lastWeekCount;
    private Integer lastMonthCount;

    private LocalDateTime requestAt;
}
