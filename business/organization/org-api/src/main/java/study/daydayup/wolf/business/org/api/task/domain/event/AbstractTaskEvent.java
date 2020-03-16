package study.daydayup.wolf.business.org.api.task.domain.event;

import lombok.Data;

/**
 * study.daydayup.wolf.business.org.api.task.domain.event
 *
 * @author Wingle
 * @since 2020/3/15 11:38 下午
 **/
@Data
public abstract class AbstractTaskEvent implements TaskEvent {
    protected Long orgId;
    protected Long staffId;

    protected Long projectId;
    protected Long taskId;
}
