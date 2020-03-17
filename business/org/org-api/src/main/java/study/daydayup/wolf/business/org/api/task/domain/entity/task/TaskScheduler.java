package study.daydayup.wolf.business.org.api.task.domain.entity.task;

import lombok.Data;
import study.daydayup.wolf.framework.layer.api.Model;

/**
 * study.daydayup.wolf.business.org.api.task.domain.entity.task
 *
 * @author Wingle
 * @since 2020/3/15 10:54 下午
 **/
@Data
public class TaskScheduler implements Model {
    private Long orgId;
    private Long staffId;
    private Long taskId;
}
