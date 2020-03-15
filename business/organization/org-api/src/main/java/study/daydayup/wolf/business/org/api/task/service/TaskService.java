package study.daydayup.wolf.business.org.api.task.service;

import study.daydayup.wolf.business.org.api.task.domain.entity.Task;
import study.daydayup.wolf.framework.layer.domain.Service;

/**
 * study.daydayup.wolf.business.org.api.task.service
 *
 * @author Wingle
 * @since 2020/3/15 11:24 下午
 **/
public interface TaskService extends Service {
    Task find(Long taskId, Long orgId);
    Task find(Long taskId, Long orgId, Integer taskType);

    int assign(Long taskId, Long orgId, Long staffId);
    int modify(Long taskId, Long orgId, Object o);

}
