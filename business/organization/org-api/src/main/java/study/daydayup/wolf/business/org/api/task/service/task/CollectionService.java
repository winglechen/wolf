package study.daydayup.wolf.business.org.api.task.service.task;

import study.daydayup.wolf.business.org.api.task.domain.entity.Task;
import study.daydayup.wolf.framework.layer.domain.Service;

/**
 * study.daydayup.wolf.business.org.api.task.service.task
 *
 * @author Wingle
 * @since 2020/3/15 11:24 下午
 **/
public interface CollectionService extends Service {

    Task findCollection(Long taskId, Long orgId);
    Task findContact(Long taskId, Long orgId);
}
