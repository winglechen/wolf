package study.daydayup.wolf.business.org.api.task.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import study.daydayup.wolf.framework.layer.api.Request;

/**
 * study.daydayup.wolf.business.org.api.task.dto
 *
 * @author Wingle
 * @since 2020/3/16 10:09 上午
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskOption implements Request {
    public static final TaskOption DEFAULT = new TaskOption();

    private boolean withDetail      = true;
    private boolean withContact     = false;
    private boolean withScheduler   = false;
    private boolean withTrade       = false;
}
